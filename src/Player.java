import java.util.ArrayList;
import java.util.List;

class Player {
    private List<Card> hand; // Список для хранения карт

    public Player() {
        hand = new ArrayList<>();
    }
    // Получение карты в руку игрока
    public void ruka(int cardValue) {
        try {
            if (cardValue < 1 || cardValue > 11) {
                throw new InvalidCardValueException("Значение карты должно быть в диапазоне от 1 до 11.");
            }
            if (hand.size() < Deck.getMaxCards()) {
                hand.add(new Card(cardValue)); // Динамическое выделение карты
            } else {
                System.out.println("Невозможно добавить карту. Рука игрока полна.");
            }
        } catch (InvalidCardValueException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    // Вывод руки игрока
    public void myvivod() {
        int sum = 0;
        System.out.print("\nМои карты: ");
        for (Card card : hand) {
            System.out.print(card.getValue() + ", ");
            sum += card.getValue();
        }
        System.out.println("Сумма: " + sum + "/21");
    }
    // Вывод руки противника без первой карты
    public void opvivodhide() {
        int sum = 0;
        System.out.print("\nКарты противника: ?, ");
        for (int i = 1; i < hand.size(); i++) {
            System.out.print(hand.get(i).getValue() + ", ");
            sum += hand.get(i).getValue();
        }
        System.out.println("Сумма: ? + " + sum + "/21");
    }
    // Вывод полной руки противника
    public void opvivodopen() {
        int sum = 0;
        System.out.print("\nКарты противника: ");
        for (Card card : hand) {
            System.out.print(card.getValue() + ", ");
            sum += card.getValue();
        }
        System.out.println("Сумма: " + sum + "/21");
    }
    // Сумма очков
    protected int getTotalValue() {
        int sum = 0;
        for (Card card : hand) {
            sum += card.getValue();
        }
        return sum;
    }
    // Вывод результата игры
    public GameResult vivodrez(Player opponent) {
        int playerScore = this.getTotalValue();
        int opponentScore = opponent.getTotalValue();
        StringBuilder resultMessage = new StringBuilder(); // Создаем экземпляр StringBuilder

        if (playerScore > 21 && opponentScore < 22) {
            resultMessage.append("У вас перебор. Вы проиграли.")
                    .append(String.format(" (Ваши очки: %d, Очки противника: %d)", playerScore, opponentScore));
        } else if (opponentScore > 21 && playerScore < 22) {
            resultMessage.append("У противника перебор. Вы выиграли.")
                    .append(String.format(" (Ваши очки: %d, Очки противника: %d)", playerScore, opponentScore));
        } else if (playerScore > 21 && opponentScore > 21) {
            if (playerScore > opponentScore) {
                resultMessage.append("У вас и противника перебор. Вы проиграли, так как имеете больше очков.")
                        .append(String.format(" (Ваши очки: %d, Очки противника: %d)", playerScore, opponentScore));
            } else {
                resultMessage.append("У вас и противника перебор. Вы выиграли, так как имеете меньше очков.")
                        .append(String.format(" (Ваши очки: %d, Очки противника: %d)", playerScore, opponentScore));
            }
        } else if (playerScore < 22 && opponentScore < 22) {
            if (playerScore > opponentScore) {
                resultMessage.append("Вы выиграли. Вы ближе к 21 очку.")
                        .append(String.format(" (Ваши очки: %d, Очки противника: %d)", playerScore, opponentScore));
            } else if (playerScore < opponentScore) {
                resultMessage.append("Вы проиграли. Противник ближе к 21 очку.")
                        .append(String.format(" (Ваши очки: %d, Очки противника: %d)", playerScore, opponentScore));
            } else {
                resultMessage.append("Ничья.");
            }
        }

        return new GameResult(resultMessage);
    }
    // Простейший искусственный интеллект для противника, который берет карту, если у него меньше 17 очков
    public boolean reshenie_ai() {
        return this.getTotalValue() < 17; // Возвращаем true (взять карту) или false (остановиться)
    }

}