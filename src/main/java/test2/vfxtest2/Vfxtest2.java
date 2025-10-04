package test2.vfxtest2;

import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import test2.vfxtest2.registry.ModItems;

@Mod(Vfxtest2.MODID)
public class Vfxtest2 {
    public static final String MODID = "vfxtest2";

    public Vfxtest2() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(modEventBus);
        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        ModItems.addToCreativeTab(event);
    }
}
