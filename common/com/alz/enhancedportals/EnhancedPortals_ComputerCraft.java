package com.alz.enhancedportals;

import java.util.logging.Level;

import net.minecraft.block.Block;

import com.alz.enhancedportals.computercraft.block.BlockDialHomeDeviceCC;
import com.alz.enhancedportals.computercraft.block.BlockPortalModifierCC;
import com.alz.enhancedportals.computercraft.tileentity.TileEntityDialHomeDeviceCC;
import com.alz.enhancedportals.computercraft.tileentity.TileEntityPortalModifierCC;
import com.alz.enhancedportals.reference.BlockIds;
import com.alz.enhancedportals.reference.Log;
import com.alz.enhancedportals.reference.Reference;
import com.alz.enhancedportals.reference.Strings;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID + "_cc", name = Reference.MOD_NAME_SHORT + " ComputerCraft", version = Reference.MOD_VERSION, dependencies = "required-after:" + Reference.MOD_ID + "@" + Reference.MOD_VERSION + ";required-after:ComputerCraft@1.52")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class EnhancedPortals_ComputerCraft
{
    @Instance(Reference.MOD_ID + "_cc")
    public static EnhancedPortals_ComputerCraft instance;

    public static Block ccComputer = null;

    @Init
    public static void init(FMLInitializationEvent event)
    {
        if (Loader.isModLoaded("ComputerCraft"))
        {
            try
            {
                ccComputer = Block.blocksList[Integer.parseInt(Class.forName("dan200.ComputerCraft").getField("computerBlockID").get(null).toString())];
                loadData();
                Log.log(Level.INFO, "Successfully loaded ComputerCraft addon.");
            }
            catch (Exception e)
            {
                Log.log(Level.SEVERE, "Could not load ComputerCraft addon.");
                e.printStackTrace(System.err);
            }
        }
        else
        {
            Log.log(Level.INFO, "Could not load ComputerCraft addon: ComputerCraft was not found.");
        }
    }

    private static void loadData()
    {
        // add recipes

        Block.blocksList[BlockIds.DIAL_HOME_DEVICE] = null;
        Block.blocksList[BlockIds.DIAL_HOME_DEVICE] = new BlockDialHomeDeviceCC();

        Block.blocksList[BlockIds.PORTAL_MODIFIER] = null;
        Block.blocksList[BlockIds.PORTAL_MODIFIER] = new BlockPortalModifierCC();

        GameRegistry.registerTileEntity(TileEntityDialHomeDeviceCC.class, Strings.Block.DIAL_HOME_DEVICE_NAME + "cc");
        GameRegistry.registerTileEntity(TileEntityPortalModifierCC.class, Strings.Block.PORTAL_MODIFIER_NAME + "cc");
    }
}
