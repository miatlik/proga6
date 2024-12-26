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
            Player player = new Player(0); // Игрок
            AdvancedPlayer opponent = new AdvancedPlayer(2,0); // Противник
            deck.vvodkolodi();
            // Начальная раздача карт
            player.ruka(deck.viborkarti());
            player.ruka(deck.viborkarti());
            opponent.ruka(deck.viborkarti());
            opponent.ruka(deck.viborkarti());
            System.out.println("Количество созданных колод: " + Deck.getDeckCount());
            // Основная игра
            while (playAgain1==true || playAgain2==true) {
                // Отображение карт
                player.myvivod();
                opponent.opvivodhide();
                //Выбор игрока
                if (playAgain1==true) {
                    System.out.println("\nНажмите 1, чтобы тянуть карту");
                    System.out.println("Нажмите 2, чтобы спасовать");
                    int choice = getPlayerChoice();

                    if (choice == 1 && player.getTotalValue() <= 21) {
                        player.ruka(deck.viborkarti());
                    }
                    else if(choice == 2 || player.getTotalValue() > 21){
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
                player.myvivod(); // Полное отображение карт игрока
                opponent.opvivodopen(); // Полное отображение карт противника
                // Оценка игры
                GameResult result = player.vivodrez(opponent);
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