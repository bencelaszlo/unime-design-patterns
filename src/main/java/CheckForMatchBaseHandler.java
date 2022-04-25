public class CheckForMatchBaseHandler implements CheckForMatchHandler {
    CheckForMatchHandler next;

    @Override
    public CheckResult check(CheckResult checkResult) {
        if (checkResult.isWin()) {
            return checkResult;
        }

        if (next != null) {
            return next.check(checkResult);
        }

        return new CheckResult(checkResult.getPlayer(), false, checkResult.getGameFields(), null);
    }

    @Override
    public void setNext(CheckForMatchHandler handler) {
        this.next = handler;
    }
}
