package combat;

import java.io.Serializable;

import combat.actions.Attack;
import combat.actions.SelfHeal;
import combat.actions.UseSpecialSkill;

public final class CombatActionRegistry implements Serializable {
    public static final CombatAction ATTACK = new Attack();

    public static final CombatAction SELF_HEAL = new SelfHeal();

    public static final CombatAction USE_SPECIAL_SKILL = new UseSpecialSkill();
}
