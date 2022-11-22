package bridge;

import bridge.domain.Bridge;
import bridge.domain.BridgeMaker;
import bridge.domain.Moving;
import bridge.domain.Result;
import bridge.view.InputView;
import bridge.view.OutputView;

import java.util.ArrayList;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 * <p>
 * 1. 제공된 BridgeGame 클래스를 활용해 구현해야 한다.
 * 2. BridgeGame에 필드(인스턴스 변수)를 추가할 수 있다.
 * 3. BridgeGame의 패키지는 변경할 수 있다.
 * 4. BridgeGame의 메서드의 이름은 변경할 수 없고, 인자와 반환 타입은 필요에 따라 추가하거나 변경할 수 있다.
 * 5. 게임 진행을 위해 필요한 메서드를 추가 하거나 변경할 수 있다.
 * </p>
 */
public class BridgeGame {
    List<String> up = new ArrayList<>();
    List<String> down = new ArrayList<>();
    private static final OutputView outputView = new OutputView();
    private static final InputView inputView = new InputView();
    private static BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());
//    List<String> bridge;
    List<List<String>> results = new ArrayList<>();
    private int count = 0;
    boolean isRight = true;
    String gameResult = "";
    boolean keepGoing = true;
    private int tryCount = 0;

    private Bridge bridge;
    private Moving moving;
    private Result result;

    public BridgeGame(Bridge bridge, Moving moving, Result result){
        this.bridge = bridge;
        this.moving = moving;
        this.result = result;
    }

//    public void start(){
//        judgeGame();
//        while(keepGoing){
//            retry();
//        }
//        outputView.printResult(results,gameResult,tryCount);
//    }

//    private void judgeGame() {
//        tryCount++;
//        while (isRight && count<bridge.size()){
//            String moving = inputView.readMoving();
//            move(bridge,moving);
//            count++;
//            outputView.printMap(results);
//        }
//        judgeResult();
//    }
    private void initVariable(){
        count = 0;
        isRight = true;
    }
    public void judgeResult(){
        result.judgeResult(moving.isRight());
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move(Bridge bridge, String inputMoving) {
        moving.checkRight(inputMoving, bridge.getBridge().get(moving.getCount()));
        moving.checkUp(inputMoving);
        moving.checkDown(inputMoving);
        moving.plusCount();
//        if(moving.getUp().contains("X") || moving.getDown().contains("X"))
//            return false;
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public boolean retry(String restart) {
        result.checkKeeping(restart);
        moving.setRight(true);
        if(!result.isKeeping())
            moving.setRight(false);
        return result.isKeeping();
    }

}
