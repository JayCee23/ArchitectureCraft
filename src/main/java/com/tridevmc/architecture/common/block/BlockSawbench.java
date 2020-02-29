/*
 * MIT License
 *
 * Copyright (c) 2017 Benjamin K
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.tridevmc.architecture.common.block;

import com.tridevmc.architecture.common.render.ModelSpec;
import com.tridevmc.architecture.common.tile.TileSawbench;
import com.tridevmc.architecture.common.ui.ArchitectureUIHooks;
import com.tridevmc.architecture.legacy.base.BaseOrientation;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class BlockSawbench extends BlockArchitecture<TileSawbench> {

    static String model = "block/sawbench.objson";
    static String[] textures = {"sawbench-wood", "sawbench-metal"};
    static ModelSpec modelSpec = new ModelSpec(model, textures);

    public BlockSawbench() {
        super(Material.WOOD, TileSawbench.class);
    }

    @Override
    public float getBlockHardness(BlockState blockState, IBlockReader worldIn, BlockPos pos) {
        return 2.0F;
    }

    @Override
    public IOrientationHandler getOrientationHandler() {
        return BaseOrientation.orient4WaysByState;
    }

    @Override
    public String[] getTextureNames() {
        return textures;
    }

    @Override
    public ModelSpec getModelSpec(BlockState state) {
        return modelSpec;
    }

    /*@Override TODO: not sure if this is gone, or just replaced.
    public boolean isFullCube(BlockState state) {
        return false;
    }*/

    /*@Override TODO: not sure if this is gone, or just replaced.
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }*/

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (!player.isCrouching()) {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof TileSawbench) {
                ArchitectureUIHooks.openGui(player, (TileSawbench) tile);
            }
            return ActionResultType.PASS;
        } else {
            return ActionResultType.FAIL;
        }
    }


    @Override
    public TileEntity createNewTileEntity(IBlockReader reader) {
        return new TileSawbench();
    }

    @Override
    public void onReplaced(BlockState state, World world, BlockPos pos, BlockState replacedWith, boolean isMoving) {
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof IInventory) {
            ItemStack items = ((IInventory) te).getStackInSlot(TileSawbench.materialSlot);
            InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), items);
        }

    }
}