class AdvancedPlayer extends Player {
    private int strategyLevel; // Уровень стратегии игрока (например, 1 - простая, 2 - сложная)

    public AdvancedPlayer(int strategyLevel) {
        super(); // Вызов конструктора базового класса
        this.strategyLevel = strategyLevel;
    }

    // Метод для принятия решения на основе уровня стратегии
    @Override
    public boolean reshenie_ai() {
        int totalValue = this.getTotalValue();
        if (strategyLevel == 1) {
            // Простая стратегия: брать карту, если меньше 17
            return totalValue < 17;
        } else if (strategyLevel == 2) {
            return totalValue < 18; // например, более агрессивная стратегия
        }
        return false; // Остановиться в других случаях
    }
}
