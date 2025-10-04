package test2.vfxtest2;

import com.mineblock11.aaa.api.client.AAALevel;
import com.mineblock11.aaa.api.client.ParticleEmitterInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

/**
 * Simple item that spawns the sample emitter from the lazer asset folder when used on a block.
 */
public class ParticleSpawnItem extends Item {

    private static final ParticleEmitterInfo SAMPLE_EFFECT = new ParticleEmitterInfo(new ResourceLocation("lazer", "sample"));

    public ParticleSpawnItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (level.isClientSide) {
            BlockPos clickedPos = context.getClickedPos();
            ParticleEmitterInfo emitter = SAMPLE_EFFECT.clone();
            emitter.position(clickedPos.getX() + 0.5D, clickedPos.getY() + 1D, clickedPos.getZ() + 0.5D);
            AAALevel.addParticle(level, false, emitter);
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}

