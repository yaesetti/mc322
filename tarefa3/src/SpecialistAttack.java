public class SpecialistAttack extends Attack {
    @Override
    public String getName() {
        return "Specialist Attack";
    }

    @Override
    public void execute(Combatant actor, Combatant target) {
        if (canExecute(actor)) {
            if (actor instanceof Specialist) {
                Specialist specialist = (Specialist) actor;
                int damage = specialist.getWeapon().getDamage();
                // Se estiver usando a arma preferida, aumenta o dano
                if (specialist.getWeapon().getClass().equals(specialist.getPreferredWeaponClass())) {
                    damage += 4;
                }
                target.receiveDamage(damage);
                System.out.printf("%s dealt %d point(s) of damage to %s!\n",
                                  specialist.getName(), damage, target.getName());
                // Outras lógicas específicas do Specialist podem ser adicionadas aqui
            }
        }
    }
}
