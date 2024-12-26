import java.util.Random;

class Deck {
    private static final int MAX_CARDS = 11; // Максимальное количество карт
    private int[] cards; // Массив для хранения значений карт
    private static int deckCount = 0; // Статическое поле для подсчета количества созданных колод
    private int size; // Текущий размер колоды
    private Random random;

    public Deck() {
        this.cards = new int[MAX_CARDS];
        this.size = MAX_CARDS; // Инициализация размера колоды
        deckCount++; // Увеличиваем счетчик при создании новой колоды
        random = new Random();
    }

    // Инициализация игральной колоды с очками от 1 до 11
    public void vvodkolodi() {
        for (int i = 0; i < MAX_CARDS; i++) {
            cards[i] = i + 1;
        }
        size = MAX_CARDS; // Инициализация размера колоды
    }
    // Случайный выбор карты из колоды
    public int viborkarti() {
        if (size <= 0) return -1; // Возвращаем невалидное значение
        int index = random.nextInt(size);
        int cardValue = cards[index];
        // Сдвиг оставшихся карт
        System.arraycopy(cards, index + 1, cards, index, size - index - 1);
        size--;
        return cardValue; // Возвращаем значение карты
    }
    // Размер колоды
    public int getSize() {
        return size;
    }
    // Возвращение максимального количества карт
    public static int getMaxCards() {
        return MAX_CARDS;
    }
    // Статический метод для получения информации о количестве созданных колод
    public static int getDeckCount() {
        return deckCount; // Возвращаем количество созданных колод
    }
}