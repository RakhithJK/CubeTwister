module ch.randelshofer.cubetwister {
    requires java.desktop;

    requires jhall;
    requires org.jhotdraw7.application;
    requires org.jhotdraw7.draw;
    requires org.monte.media;
    requires org.kociemba.twophase;

    exports ch.randelshofer.rubik;
    exports ch.randelshofer.rubik.parser;
}