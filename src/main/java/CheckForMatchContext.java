public class CheckForMatchContext {
    private CheckForMatchStrategy strategy;

    public void setContext(CheckForMatchStrategy strategy) {
        this.strategy = strategy;
    }

    public CheckForMatchStrategy.CheckResult checkForMatch(FieldButton[] gameFields, Players player) throws Exception {
        if (strategy == null) {
            throw new Exception("Strategy Not Found");
        }

        return strategy.check(gameFields, player);
    }
}
