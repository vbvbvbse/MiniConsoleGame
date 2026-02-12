package gameOrchestrator;

import menu.MenuDecisionStrategyOfDay;
import output.Output;
import reader.Reader;

import java.util.ArrayList;
import java.util.List;

public class DayDecisionHandler {
    //обработка дней, которые вписываются в ограничения кол-вы дней для режима
    private int firstDay;
    private int countDays;
    private int lastDay;
    private List<Integer> days;
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
    private int howLongDecisionLegitimate(){
        int input;
        while(true){
            input = Reader.readInt("Введите сколько дней подряд ваше решение относительно режима работы программы должно действовать!");
            if(input<=0){
                System.out.println("Количество дней должно быть больше 0!");
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


    public void isItLast(int day){
        int ld = lastDay;
        if(day == (lastDay - 1)) {
            days.clear();
        }
    }
    public boolean shouldCreateNew(int day){
        if(days.contains(day)){
            return true;
        }else{
            return false;
        }
    }
    public void dayDecisionHandle(int day){
        firstDay = day;
        decisionStrategy = makeDecisionDayType();
        countDays = howLongDecisionLegitimate();
        lastDay = firstDay+countDays;
        days = new ArrayList();
        for (int i = firstDay; i < lastDay; i++) {
            days.add(i);
        }
    }

    public void dayDecisionProvider(int day){
        if(days.contains(day)){
            output.outliner();
            System.out.printf("День %d: \n",day);
            dayDecisionTypeHandler.dayDecisionTypeHandle(decisionStrategy);
        }
    }
}
