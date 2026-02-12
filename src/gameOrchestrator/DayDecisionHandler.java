package gameOrchestrator;

import menu.MenuDecisionStrategyOfDay;
import output.Output;
import reader.Reader;


public class DayDecisionHandler {
    //обработка дней, которые вписываются в ограничения кол-вы дней для режима
    private int firstDay;
    private int countDays;
    private int lastDay;
    //di классы
    private Output output;
    private DayDecisionTypeHandler dayDecisionTypeHandler;
    //запоминаем тип потребности
    private MenuDecisionStrategyOfDay decisionStrategy;

    public DayDecisionHandler(Output output, DayDecisionTypeHandler dayDecisionTypeHandler) {
        this.output = output;
        this.dayDecisionTypeHandler = dayDecisionTypeHandler;
    }
    //кол-во дней под режим
    private int howLongDecisionLegitimate(int day){
        int remainingDays = 31 - day;
        int input;
        while(true){
            input = Reader.readInt("Введите сколько дней подряд ваше решение относительно режима работы программы должно действовать!");
            if (input <= 0) {
                System.out.println("Количество дней должно быть больше 0!");
                if (input > remainingDays) {
                    System.out.println("Количество дней не может быть больше оставшегося количества дней (" + remainingDays + ")!");
                }
            } else{
                break;
            }
        }
        System.out.println("Теперь "+input+" дней подряд будет соблюдаться режим работы программы:");
        return input;
    }
    //выбираем режим работы программы
    private MenuDecisionStrategyOfDay makeDecisionDayType(){
        int input = Reader.readInt("Введите будете ли вы сами выбирать действия или программа будет принимать решения случайным образом?(1/2)");
        while (true){
            if(input == 1){
                return MenuDecisionStrategyOfDay.HumanMakeDecision;
            }else if(input == 2){
                return MenuDecisionStrategyOfDay.AutoMakeDecision;
            }else{
                System.out.println("Вы ввели неверный символ(введите либо 1, либо 2)");
            }
        }
    }

    public boolean checkDayIsInRange(int day){
        if(day>=firstDay&&day<lastDay){
            return true;
        }else{
            return false;
        }
    }
    public void dayDecisionHandle(int day){
        firstDay = day;
        decisionStrategy = makeDecisionDayType();
        countDays = howLongDecisionLegitimate(day);
        lastDay = firstDay+countDays;
    }

    public void dayDecisionProvider(int day){
        output.outliner();
        System.out.printf("День %d: \n",day);
        dayDecisionTypeHandler.dayDecisionTypeHandle(decisionStrategy);
    }
}
