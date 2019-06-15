package cn.mcmod.sakura;

import cn.mcmod.sakura.block.BlockLoader;
import cn.mcmod.sakura.client.SakuraParticleType;
import cn.mcmod.sakura.client.particle.ParticleMapleRedLeaf;
import cn.mcmod.sakura.entity.SakuraEntityRegister;
import cn.mcmod.sakura.item.ItemLoader;
import cn.mcmod.sakura.tileentity.TileEntityRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    public static ResourceLocation leafTexture = new ResourceLocation(SakuraMain.MODID, "textures/particles/leaf.png");

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        BlockLoader.registerRenders();
        ItemLoader.registerRenders();
        SakuraEntityRegister.entityRender();

        TileEntityRegistry.render();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);

    }

    @Override
    public World getClientWorld() {

        return FMLClientHandler.instance().getClient().world;

    }

    @Override
    public void spawnParticle(SakuraParticleType particleType, double x, double y, double z, double velX, double velY, double velZ) {

        Minecraft mc = Minecraft.getMinecraft();
        World world = mc.world;

        if (world == null) {

            return;

        }

        if (mc.effectRenderer != null) {

            int i = mc.gameSettings.particleSetting;

            if (i == 1 && world.rand.nextInt(3) == 0) {

                i = 2;

            }

            Particle particle = null;

            switch (particleType) {
                case MAPLERED:
                    particle = new ParticleMapleRedLeaf(world, x, y, z, velX, velY, velZ);
                    break;

            }

            if (particle != null) {
                mc.effectRenderer.addEffect(particle);
            }

        }
    }
}