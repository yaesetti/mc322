package combat.actions;

import characters.Hero;
import combat.CombatAction;
import combat.Combatant;
import exceptions.CharacterKnocked;
import exceptions.InsufficientWillPoints;
import items.weapons.Pistol;
import items.weapons.Sword;
import utils.Dice;

/**
 * Represents a basic attack action that can be executed by a {@link Combatant}.
 * The attack consumes will points, deals damage to the target, and may trigger
 * weapon-specific effects such as bullet consumption or sword sharpness reduction.
 * After execution, the actor's luck is recalculated.
 */
public class Attack implements CombatAction {

    /**
     * Returns the name of the action.
     *
     * @return the string "Attack"
     */
    @Override
    public String getName() {
        return "Attack";
    }

    /**
     * Executes the attack action from the actor onto the target.
     *
     * @param actor  the combatant performing the attack
     * @param target the combatant receiving the damage
     * @throws InsufficientWillPoints if the actor has less than 1 will point
     * @throws CharacterKnocked       if the actor is currently knocked out
     */
    @Override
    public void execute(Combatant actor, Combatant target)
            throws InsufficientWillPoints, CharacterKnocked {

        if (actor.getIsKnocked()) {
            throw new CharacterKnocked();
        }

        if (actor.getWillPoints() < 1) {
            throw new InsufficientWillPoints();
        }

        int damage = actor.getAttackDamage();
        target.receiveDamage(damage);
        actor.setWillPoints(actor.getWillPoints() - 1);

        if (target.getIsKnocked()) {
            System.out.printf("%s dealt %d point(s) of damage to %s and knocked them!\n",
                    actor.getName(), damage, target.getName());
        } else {
            System.out.printf("%s dealt %d point(s) of damage to %s!\n",
                    actor.getName(), damage, target.getName());
        }

        if (actor instanceof Hero) {
            if (actor.getWeapon().getClass().equals(Sword.class) && !actor.getLuck()) {
                Sword sword = (Sword) actor.getWeapon();
                sword.setSharpness(sword.getSharpness() - 1);
            }

            if (actor.getWeapon().getClass().equals(Pistol.class)) {
                Pistol pistol = (Pistol) actor.getWeapon();
                pistol.setBullet(pistol.getBullet() - 1);
            }
        }

        actor.setLuck(Dice.roll(1, 100) <= 30);
    }
}
