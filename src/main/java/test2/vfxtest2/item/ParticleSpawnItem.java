package test2.vfxtest2.item;

import dev.foundationgames.aaa.particles.AAALevel;
import dev.foundationgames.aaa.particles.emitter.ParticleEmitterInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class ParticleSpawnItem extends Item {
    private static final ParticleEmitterInfo HERALD =
            new ParticleEmitterInfo(new ResourceLocation("aaa-particle-example", "herald"));

    public ParticleSpawnItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos blockPos = context.getClickedPos();
        if (level.isClientSide()) {
            AAALevel.addParticle(level, false, HERALD.clone().position(
                    blockPos.getX() + 0.5D,
                    blockPos.getY() + 1.0D,
                    blockPos.getZ() + 0.5D
            ));
        }
        return super.useOn(context);
    }
}
