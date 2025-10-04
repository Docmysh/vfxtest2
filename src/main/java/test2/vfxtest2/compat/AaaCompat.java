package test2.vfxtest2.compat;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import org.slf4j.Logger;
import test2.vfxtest2.Vfxtest2;

/**
 * Optional integration with the AAA particle system. The dependency is resolved reflectively so the
 * mod can still build and run without the library present.
 */
public final class AaaCompat {
    private static final Logger LOGGER = Vfxtest2.LOGGER;
    private static final String AAA_MOD_ID = "aaa";
    private static final String EMITTER_CLASS = "com.mineblock11.aaa.api.client.ParticleEmitterInfo";
    private static final String AAA_LEVEL_CLASS = "com.mineblock11.aaa.api.client.AAALevel";

    private AaaCompat() {
    }

    /**
     * Attempts to spawn an AAA particle emitter at the supplied position.
     *
     * @return {@code true} if the emitter was spawned successfully, {@code false} otherwise.
     */
    public static boolean spawnEmitter(Level level, BlockPos position, ResourceLocation emitterId) {
        if (!ModList.get().isLoaded(AAA_MOD_ID)) {
            return false;
        }

        try {
            Class<?> emitterClass = Class.forName(EMITTER_CLASS);
            Object emitter = emitterClass.getConstructor(ResourceLocation.class).newInstance(emitterId);
            emitterClass.getMethod("position", double.class, double.class, double.class)
                    .invoke(emitter, position.getX() + 0.5D, position.getY() + 1.0D, position.getZ() + 0.5D);

            Class<?> aaaLevelClass = Class.forName(AAA_LEVEL_CLASS);
            aaaLevelClass.getMethod("addParticle", Level.class, boolean.class, emitterClass)
                    .invoke(null, level, false, emitter);
            return true;
        } catch (ReflectiveOperationException | RuntimeException ex) {
            LOGGER.warn("Failed to spawn AAA particle emitter '{}'", emitterId, ex);
        }

        return false;
    }
}
