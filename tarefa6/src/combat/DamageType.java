package combat;

/**
 * Represents the types of damage that can be inflicted during combat.
 * Each damage type may interact differently with character resistances,
 * vulnerabilities, or environmental effects.
 */
public enum DamageType {

    /** Physical damage caused by blunt force or impact. */
    Bludgeoning,

    /** Physical damage caused by cutting or slicing. */
    Slashing,

    /** Physical damage caused by piercing or stabbing. */
    Piercing,

    /** Magical or kinetic energy-based damage. */
    Force,

    /** Damage caused by heat or fire. */
    Fire,

    /** Damage caused by electricity or lightning. */
    Thunder,

    /** Damage caused by cold or freezing temperatures. */
    Cold;
}
