package test2.vfxtest2;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import test2.vfxtest2.compat.AaaCompat;

/**
 * Simple item that spawns the sample emitter from the lazer asset folder when used on a block.
 */
public class ParticleSpawnItem extends Item {

    private static final ResourceLocation SAMPLE_EFFECT = ResourceLocation.fromNamespaceAndPath("lazer", "sample");

    public ParticleSpawnItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (level.isClientSide) {
            BlockPos clickedPos = context.getClickedPos();
            boolean spawned = AaaCompat.spawnEmitter(level, clickedPos, SAMPLE_EFFECT);
            if (!spawned) {
                spawnFallbackParticles(level, clickedPos);
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    private static void spawnFallbackParticles(Level level, BlockPos origin) {
        var random = level.random;
        for (int i = 0; i < 20; i++) {
            double x = origin.getX() + 0.5D + (random.nextDouble() - 0.5D);
            double y = origin.getY() + 1.0D + random.nextDouble() * 0.5D;
            double z = origin.getZ() + 0.5D + (random.nextDouble() - 0.5D);
            level.addParticle(ParticleTypes.END_ROD, x, y, z, 0.0D, 0.02D, 0.0D);
        }
    }
}
