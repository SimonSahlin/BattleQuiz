package app_game;

public class GuidePrint {


    //The Battlequiz printout
    public void battleQuizLogo(){
        System.out.println(
                "  ______   __  __   ______       ____     ___       ______   ______   __       ______     ____      __  __   ____   _____      \n" +
                        " /_  __/  / / / /  / ____/      / __ )   /   |     /_  __/  /_  __/  / /      / ____/    / __ \\    / / / /  /  _/  /__  /      \n" +
                        "  / /    / /_/ /  / __/        / __  |  / /| |      / /      / /    / /      / __/      / / / /   / / / /   / /      / /       \n" +
                        " / /    / __  /  / /___       / /_/ /  / ___ |     / /      / /    / /___   / /___     / /_/ /   / /_/ /  _/ /      / /__      \n" +
                        "/_/    /_/ /_/  /_____/      /_____/  /_/  |_|    /_/      /_/    /_____/  /_____/     \\___\\_\\   \\____/  /___/     /____/      \n " +
                        " <><><><><><><><><><><>  A  p r o d u c t  o f  G R O U P  F O U R  g a m e  s t u d i o s  <><><><><><><><><><><>    \n  "
        );
    }

    //Main menu at start-up of program
    public void mainMenuFull(){
        System.out.println(
                        "   ||======================================|| \n" +
                        "   ||     > > > MAIN MENU  < < <           || \n" +
                        "   ||     [1] -> Score board               || \n" +
                        "   ||     [2] -> Play BattleQuiz           || \n" +
                        "   ||     [0] -> Exit program              || \n" +
                        "   ||     [admin] -> Admin menu ( DEMO )   || \n" +
                        "   ||======================================|| \n");
    }

    //Mini-Menu for  choices down the road in the program
    public void mainMenuMini(){
        System.out.println(
                "\n"                                                                         +
                        "   ||======================================================================||  \n" +
                        "   ||[1]->Score board [2]->Play BattleQuiz [0]-> Exit [admin]-> admin Menu ||  \n" +
                        "   ||======================================================================||  \n"
        );
    }

    //Menu at first entry of Admin Menu
    public void adminMenuFull(){
        System.out.println(
                "   ||==============================================================|| \n" +
                        "   ||                      > > ADMIN MENU  < <                     || \n" +
                        "   ||                                                              || \n" +
                        "   ||     [1] -> Show Player record  [6] -> Remove Question        || \n" +
                        "   ||     [2] -> Remove Player       [7] -> Edit Question          || \n" +
                        "   ||     [3] -> Clear Player record [8] -> Fabric setting Q-bank  || \n" +
                        "   ||     [4] -> Show all questions  [0] -> Back to Main menu      || \n" +
                        "   ||     [5] -> Add Question                                      || \n" +
                        "   ||==============================================================|| \n");

    }

    // Mini menu for choices down the road in the program
    public void adminMenuMini(){
        System.out.println(
                "\n" +
                        "   ||========================================================================================||\n" +
                        "   ||   [1]->P. Record [2]->Remove P. [3]->Clear Record [4]->show all Q. [5]->Add Q.         ||\n" +
                        "   ||   [6]->Remove Q. [7]->Edit Q. [8]->Reset Q.-bank                [0]->Main menu         ||\n" +
                        "   ||========================================================================================||\n"
        );

    }


}
