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

package com.tridevmc.architecture.client.render.model;

import com.tridevmc.architecture.client.render.target.RenderTargetBase;
import com.tridevmc.architecture.client.render.texture.ITexture;
import com.tridevmc.architecture.common.helpers.Trans3;
import net.minecraft.util.math.AxisAlignedBB;

import java.util.List;

public interface IRenderableModel {
    AxisAlignedBB getBounds();

    void addBoxesToList(Trans3 t, List<AxisAlignedBB> list);

    ArchitectureModelData generateModelData(Trans3 t, int baseColourMult, int secondaryColourMult, ITexture... textures);

    void render(Trans3 t, RenderTargetBase target, int baseColourMult, int secondaryColourMult, ITexture[] textures);
}
