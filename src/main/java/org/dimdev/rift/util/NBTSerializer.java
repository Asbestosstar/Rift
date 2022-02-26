package org.dimdev.rift.util;


import javax.annotation.Nonnull;

import net.minecraft.nbt.CompoundNBT;

/**
 * Base interface for (de)serializing objects of type {@code T}
 * to and from {@link NBTTagCompound} tag compounds
 */
public interface NBTSerializer<T> {
    /**
     * Writes an object {@code T} to the given compound
     * @param instance The instance to be serialized
     * @return The written tag compound
     */
    @Nonnull
    CompoundNBT serialize(@Nonnull T instance);

    /**
     * Reads an object {@code T} from the given compound
     * @param compound The tag compound to be read from
     * @return An instance of {@code T}
     */
    @Nonnull
    T deserialize(@Nonnull CompoundNBT compound);
}
