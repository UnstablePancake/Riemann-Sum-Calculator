public class Calculate {

    private double minInt;
    private double maxInt;
    private double subint;
    private String spreadsheet;

    public Calculate(double minInt, double maxInt, double subint) {
        this.minInt = minInt;
        this.maxInt = maxInt;
        this.subint = subint;
    }

    private double getIncrement() {
        return (maxInt - minInt) / subint;
    }

    private double calcEquation(double x) {
        return Math.pow(x, 2);
    }

    public void calcLeft() {
        double sum = 0;

        for (double i = minInt; roundNumber(i) < maxInt; i += getIncrement()) {
            sum += roundNumber(getIncrement() * calcEquation(i));
            addTextToSpreadsheet(i, calcEquation(i));
        }
        setAnswer(sum);
    }

    public void calcRight() {
        double sum = 0;

        for (double i = minInt + getIncrement(); roundNumber(i) <= maxInt; i+= getIncrement()) {
            sum += getIncrement() * calcEquation(i);

            addTextToSpreadsheet(roundNumber(i), calcEquation(i));
        }
        setAnswer(sum);
    }

    public void calcMiddle() {
        double sum = 0;

        for (double i = (minInt + getIncrement()) / 2.0; roundNumber(i) < maxInt; i+= getIncrement()) {
            sum += getIncrement() * calcEquation(i);

            addTextToSpreadsheet(roundNumber(i), calcEquation(i));
        }
        setAnswer(sum);
    }

    private void setAnswer(double answer) {
        Window.txtAnswer.setText(String.valueOf(roundNumber(answer)));
    }

    private double roundNumber(double number) {
        return Math.round(number * 1000.0) / 1000.0;
    }

    public void addTextToSpreadsheet(double x, double fx) {
        spreadsheet += "\n\t" + roundNumber(x) + "\t" + roundNumber(fx);
    }

    public void setSpreadsheet() {
        Window.sheet.setText(spreadsheet);
    }

    public void resetSpreadsheet() {
        spreadsheet = "\tx\tf(x)\n\t----\t----";
    }
}
