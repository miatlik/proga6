class AdvancedPlayer extends Player {
    private int strategyLevel; // Уровень стратегии игрока (например, 1 - простая, 2 - сложная)

    public AdvancedPlayer(int strategyLevel) {
        super(); // Вызов конструктора базового класса
        this.strategyLevel = strategyLevel;
    }

    // Перегрузка метода reshenie_ai() с параметром
    public boolean reshenie_ai(boolean useBaseLogic) {
        if (useBaseLogic) {
            // Вызов метода базового класса
            return super.reshenie_ai(); // Используем базовую логику
        } else {
            // Собственная логика без вызова базового класса
            int totalValue = this.getTotalValue();
            if (strategyLevel == 1) {
                return totalValue < 17; // Простая стратегия
            } else if (strategyLevel == 2) {
                return totalValue < 18; // Более агрессивная стратегия
            }
            return false; // Остановиться в других случаях
        }
    }

    // Перегрузка метода reshenie_ai() без параметров
    @Override
    public boolean reshenie_ai() {
        // Используем собственную логику
        int totalValue = this.getTotalValue();
        if (strategyLevel == 1) {
            return totalValue < 17; // Простая стратегия
        } else if (strategyLevel == 2) {
            return totalValue < 18; // Более агрессивная стратегия
        }
        return false; // Остановиться в других случаях
    }
}
