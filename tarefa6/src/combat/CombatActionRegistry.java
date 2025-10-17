package combat;

import java.io.Serializable;

import combat.actions.Attack;
import combat.actions.SelfHeal;
import combat.actions.UseSpecialSkill;

/**
 * {@code CombatActionRegistry} serves as a centralized registry for all predefined
 * {@link CombatAction} instances used in the game.
 * <p>
 * This class provides static references to commonly used combat actions such as
 * attacking, self-healing, and using special skills. By storing them here, the game
 * can easily access shared action instances without repeatedly creating new ones.
 * </p>
 *
 * <p><b>Design Note:</b> This class is declared {@code final} to prevent inheritance,
 * and all members are {@code static} so that no instantiation is required.</p>
 *
 * @see CombatAction
 * @see Attack
 * @see SelfHeal
 * @see UseSpecialSkill
 */
public final class CombatActionRegistry implements Serializable {

    /**
     * Represents a basic physical attack action.
     * <p>
     * This action typically reduces the opponent's health based on
     * the attacker's strength and weapon damage.
     * </p>
     */
    public static final CombatAction ATTACK = new Attack();

    /**
     * Represents a self-healing action.
     * <p>
     * When executed, this action restores part of the user's health
     * or will points, depending on the implementation.
     * </p>
     */
    public static final CombatAction SELF_HEAL = new SelfHeal();

    /**
     * Represents a special skill action unique to each combatant.
     * <p>
     * This action invokes the combatant’s class-specific or ability-based
     * special skill (e.g., a spell, critical attack, or defense move).
     * </p>
     */
    public static final CombatAction USE_SPECIAL_SKILL = new UseSpecialSkill();
}
