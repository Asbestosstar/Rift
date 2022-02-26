package org.dimdev.rift.util;

import javax.annotation.Nonnull;

import net.minecraft.nbt.CompoundNBT;

/**
 * Base interface for (de)serializable objects to serialize
 * themselves to and from {@link NBTTagCompound} tag compounds
 */
public interface NBTSerializable {
    /**
     * Writes this object's data to the given compound
     * @param compound The tag compound to be written to
     * @return The written tag compound
     */
    @Nonnull
    CompoundNBT serialize(@Nonnull CompoundNBT compound);

    /**
     * Reads this object's data from the given compound
     * @param compound The tag compound to be read from
     * @return The given tag compound
     */
    @Nonnull
    CompoundNBT deserialize(@Nonnull CompoundNBT compound);

    /**
     * Writes this object's data to a new tag compound
     * @return The written tag compound
     */
    @Nonnull
    default CompoundNBT writeToNBT() {
        return this.serialize(new CompoundNBT());
    }
}
