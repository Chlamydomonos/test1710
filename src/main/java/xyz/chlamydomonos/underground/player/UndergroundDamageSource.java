package xyz.chlamydomonos.underground.player;

import net.minecraft.util.DamageSource;

public class UndergroundDamageSource extends DamageSource
{
    public static final UndergroundDamageSource INSTANCE = new UndergroundDamageSource();

    public UndergroundDamageSource()
    {
        super("underground");
        this.setDamageBypassesArmor();
        this.setDamageAllowedInCreativeMode();
    }
}
