package Model;
@FunctionalInterface
public interface LogicNode {

    public boolean call();

    public static LogicNode variable(boolean[] a, int i, char name) {
        return new LogicNode() {
            private int index = i;
            private boolean[] arrays = a;

            @Override
            public boolean call() {
                return arrays[index];
            }

            @Override
            public String toString() {
                return String.valueOf(name);
            }
        };
    }

    public static LogicNode verdadero() {
        return new LogicNode() {
            @Override
            public boolean call() {
                return true;
            }

            @Override
            public String toString() {
                return "V";
            }
        };
    }

    public static LogicNode falso() {
        return new LogicNode() {
            @Override
            public boolean call() {
                return false;
            }

            @Override
            public String toString() {
                return "F";
            }
        };
    }

    public static LogicNode and(LogicNode a, LogicNode b) {
        return new LogicNode() {
            @Override
            public boolean call() {
                return a.call() && b.call();
            }

            @Override
            public String toString() {
                return "( " + a.toString() + " and " + b.toString() + " )";
            }
        };

    }

    public static LogicNode or(LogicNode a, LogicNode b) {
        return new LogicNode() {
            @Override
            public boolean call() {
                return a.call() || b.call();
            }

            @Override
            public String toString() {
                return "( " + a.toString() + " or " + b.toString() + " )";
            }
        };
    }

    public static LogicNode not(LogicNode a) {
        return new LogicNode() {
            @Override
            public boolean call() {
                return !a.call();
            }

            @Override
            public String toString() {
                return "-" + a.toString();
            }
        };
    }

    public static LogicNode implica(LogicNode a, LogicNode b) {
        return new LogicNode() {
            @Override
            public boolean call() {
                boolean ra = a.call(), rb = b.call();
                return ra && !rb;
            }

            @Override
            public String toString() {
                return "( " + a.toString() + " implica " + b.toString() + " )";
            }
        };
    }
    
    public static LogicNode dobleImplica(LogicNode a, LogicNode b) {
        return new LogicNode() {
            @Override
            public boolean call() {
                boolean ra = a.call(), rb = b.call();
                return ra && rb || !ra && !rb;
            }

            @Override
            public String toString() {
                return "( " + a.toString() + " dobleImplica " + b.toString() + " )";
            }
        };
    }
}
