import java.util.Random;
import java.util.Scanner;

public class BlackjakeGame {
    private static final int MAX_CARDS = 11; // Максимальное количество карт
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();
    public static void main(String[] args) {
        char ch;
        do {
            boolean playAgain1 = true;
            boolean playAgain2 = true;
            Deck deck = new Deck();
            Player originalPlayer = new Player(0); // Игрок
            AdvancedPlayer opponent = new AdvancedPlayer(2,0); // Противник
            deck.vvodkolodi();
            // Начальная раздача карт
            originalPlayer.ruka(deck.viborkarti());
            opponent.ruka(deck.viborkarti());
            opponent.ruka(deck.viborkarti());
            //Клонирование
            try {

                Player shallowClone = (Player) originalPlayer.clone();
                Player deepClone = originalPlayer.deepClone();
                System.out.println("Мелкое клонирование:");
                System.out.println("Оригинальный игрок: " + originalPlayer.getTotalValue());
                System.out.println("Клонированный игрок: " + shallowClone.getTotalValue());

                System.out.println("\nГлубокое клонирование:");
                System.out.println("Оригинальный игрок: " + originalPlayer.getTotalValue());
                System.out.println("Глубоко клонированный игрок: " + deepClone.getTotalValue());

                originalPlayer.ruka(deck.viborkarti());
                System.out.println("\nПосле изменения оригинала:");
                System.out.println("Оригинальный игрок: " + originalPlayer.getTotalValue());
                System.out.println("Клонированный игрок: " + shallowClone.getTotalValue()); // Эта сумма изменится

                System.out.println("\nПосле изменения оригинала:");
                System.out.println("Оригинальный игрок: " + originalPlayer.getTotalValue());
                System.out.println("Глубоко клонированный игрок: " + deepClone.getTotalValue()); // Эта сумма не изменится

            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            System.out.println("Количество созданных колод: " + Deck.getDeckCount());
            // Основная игра
            while (playAgain1==true || playAgain2==true) {
                // Отображение карт
                originalPlayer.myvivod();
                opponent.opvivodhide();
                //Выбор игрока
                if (playAgain1==true) {
                    System.out.println("\nНажмите 1, чтобы тянуть карту");
                    System.out.println("Нажмите 2, чтобы спасовать");
                    int choice = getPlayerChoice();

                    if (choice == 1 && originalPlayer.getTotalValue() <= 21) {
                        originalPlayer.ruka(deck.viborkarti());
                    }
                    else if(choice == 2 || originalPlayer.getTotalValue() > 21){
                        System.out.println("Вы спасовали");
                        playAgain1=false;
                    }
                }
                // Выбор противника
                if(playAgain2==true) {
                    if (opponent.reshenie_ai()) {
                        opponent.ruka(deck.viborkarti());
                    } else {
                        System.out.println("\nПротивник спасовал");
                        playAgain2 = false;
                    }
                }
            }
            // Вывод результатов
            if (playAgain1==false && playAgain2==false) {
                originalPlayer.myvivod(); // Полное отображение карт игрока
                opponent.opvivodopen(); // Полное отображение карт противника
                // Оценка игры
                GameResult result = originalPlayer.vivodrez(opponent);
                System.out.println(result.getResultMessage());
            }
            System.out.println("\nНажмите q, чтобы выйти или любую другую клавишу, чтобы сыграть заново");
            ch = scanner.nextLine().charAt(0);
        } while (ch != 'q');
    }
    //Обработка ошибок
    private static int getPlayerChoice() {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 2) {
                    throw new NumberFormatException();
                }
                return choice;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка. Выберите 1 или 2: ");
            }
        }
    }

}