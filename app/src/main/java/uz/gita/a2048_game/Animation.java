package uz.gita.a2048_game;

public class Animation {
}
//public class GameView extends androidx.appcompat.widget.AppCompatImageView {
//
//
//    private static final AppRepositoryImpl ap = new AppRepositoryImpl();
//
//    private Paint paint;
//    private Paint textPaint;
//    private int   width;
//    private int   height;
//
//
//    public GameView(Context context) {
//        super(context);
//        initialize();
//
//    }
//
//
//    public GameView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        initialize();
//    }
//
//
//    public GameView(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//        initialize();
//    }
//
//
//    private void initialize() {
//        paint = new Paint();
//        paint.setColor(Color.parseColor("#aaaaaa"));
//        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//        paint.setAntiAlias(true);
//
//        textPaint = new Paint();
//        textPaint.setColor(Color.parseColor("#000000"));
//        textPaint.setStyle(Paint.Style.FILL);
//        textPaint.setAntiAlias(true);
//        textPaint.setTextSize(G.convertDpToPixel(48));
//        textPaint.setTextAlign(Paint.Align.CENTER);
//    }
//
//
//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        super.onLayout(changed, left, top, right, bottom);
//        if (getWidth() < getHeight()) {
//            width = getWidth();
//        } else {
//            width = getHeight();
//        }
//
//    }
//
//
//    public void resetGame() {
//        G.score = 0;
//        G.updateScore();
//
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                G.cells[i][j] = 0;
//            }
//        }
//
//        dropNextNumber();
//        dropNextNumber();
//
//
//    }
//
//
//    private void dropNextNumber() {
//        boolean gameFinished = true;
//
//        int freeCells = 0;
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                if (G.cells[i][j] == 0) {
//                    freeCells++;
//                    if (freeCells > 1) {
//                        gameFinished = false;
//                        break;
//                    }
//                }
//            }
//        }
//
//        if (gameFinished) {
//            Toast.makeText(getContext(), "You Lose!", Toast.LENGTH_SHORT).show();
//        } else {
//            while (true) {
//                int randomX = (int) Math.floor(Math.random() * 4);
//                int randomY = (int) Math.floor(Math.random() * 4);
//                if (G.cells[randomX][randomY] == 0) {
//                    G.cells[randomX][randomY] = 2;
//                    break;
//                }
//            }
//
//            invalidate();
//        }
//    }
//
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        float size = width / 4.0f;
//
//        float padding = 4;
//        float halfSize = size / 2.0f - padding;
//        float offset = getWidth() - 4.0f * size;
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                paint.setColor(Color.parseColor(getNumberColor(G.cells[i][j])));
//                float centerX = (j + 0.5f) * size + offset / 2.0f;
//                float centerY = (i + 0.5f) * size;
//                @SuppressLint("DrawAllocation") RectF rect = new RectF(centerX - halfSize, centerY - halfSize, centerX + halfSize, centerY + halfSize);
//                canvas.drawRoundRect(rect, G.convertDpToPixel(8), G.convertDpToPixel(8), paint);
//                if (G.cells[i][j] != 0) {
//                    String textToDraw = new String("" + G.cells[i][j]);
//                    Rect bounds = new Rect();
//                    textPaint.getTextBounds(textToDraw, 0, textToDraw.length(), bounds);
//                    canvas.drawText(textToDraw, centerX, centerY + (bounds.bottom - bounds.top) / 2, textPaint);
//                }
//            }
//        }
//    }
//
//
//    private void goToUp() {
//        for (int j = 0; j < 4; j++) {
//            for (int i = 0; i < 4; i++) {
//                if (i > 0) {
//                    int k = i;
//                    while (k > 0 && G.cells[k - 1][j] == 0) {
//                        G.cells[k - 1][j] = G.cells[k][j];
//                        G.cells[k][j] = 0;
//                        k--;
//                    }
//
//                    if (k > 0) {
//                        if (G.cells[k - 1][j] == G.cells[k][j]) {
//                            power(k - 1, j);
//                            G.cells[k][j] = 0;
//                        }
//                    }
//                }
//            }
//        }
//        invalidate();
//    }
//
//
//    public void power(int i, int j) {
//        G.cells[i][j] *= 2;
//        G.score += G.cells[i][j];
//        G.updateScore();
//    }
//
//
//    private void goToDown() {
//        for (int j = 3; j >= 0; j--) {
//            for (int i = 3; i >= 0; i--) {
//                if (i < 3) {
//                    int k = i;
//                    while (k < 3 && G.cells[k + 1][j] == 0) {
//                        G.cells[k + 1][j] = G.cells[k][j];
//                        G.cells[k][j] = 0;
//                        k++;
//                    }
//
//                    if (k < 3) {
//                        if (G.cells[k + 1][j] == G.cells[k][j]) {
//                            power(k + 1, j);
//                            G.cells[k][j] = 0;
//                        }
//                    }
//                }
//            }
//        }
//        invalidate();
//    }
//
// */
//    private void goToLeft() {
//        for (int i = 0; i < 4; i++) {
//            for (int j = 0; j < 4; j++) {
//                if (j > 0) {
//                    int k = j;
//                    while (k > 0 && G.cells[i][k - 1] == 0) {
//                        G.cells[i][k - 1] = G.cells[i][k];
//                        G.cells[i][k] = 0;
//                        k--;
//                    }
//
//                    if (k > 0) {
//                        if (G.cells[i][k - 1] == G.cells[i][k]) {
//                            power(i, k - 1);
//                            G.cells[i][k] = 0;
//                        }
//                    }
//                }
//            }
//        }
//        invalidate();
//    }
//
//
//    private void goToRight() {
//        for (int i = 3; i >= 0; i--) {
//            for (int j = 3; j >= 0; j--) {
//                if (j < 3) {
//                    int k = j;
//                    while (k < 3 && G.cells[i][k + 1] == 0) {
//                        G.cells[i][k + 1] = G.cells[i][k];
//                        G.cells[i][k] = 0;
//                        k++;
//                    }
//
//                    if (k < 3) {
//                        if (G.cells[i][k + 1] == G.cells[i][k]) {
//                            power(i, k + 1);
//                            G.cells[i][k] = 0;
//                        }
//                    }
//                }
//            }
//        }
//        invalidate();
//    }
//
//    private float            lastDownX;
//    private float            lastDownY;
//
//    private boolean          movePending;
//
//    private static final int MODE_LEFT  = 1;
//    private static final int MODE_RIGHT = 2;
//    private static final int MODE_UP    = 3;
//    private static final int MODE_DOWN  = 4;
//
//
//    private String getNumberColor(int number) {
//        switch (number) {
//            case 0:
//                return "#ccc0b3";
//
//            case 2:
//                return "#eee4da";
//
//            case 4:
//                return "#ede0c8";
//
//            case 8:
//                return "#f2b179";
//
//            case 16:
//                return "#f59563";
//
//            case 32:
//                return "#f67c5f";
//
//            case 64:
//                return "#f65e3b";
//        }
//
//        return "#ccc0b3";
//    }
//
//
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                lastDownX = event.getRawX();
//                lastDownY = event.getRawY();
//                movePending = true;
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//                if (!movePending) {
//                    return true;
//                }
//
//                int mode = 0;
//                float diffX = lastDownX - event.getRawX();
//                float diffY = lastDownY - event.getRawY();
//
//                if (Math.abs(diffX) > 10 && Math.abs(diffX) > 2 * Math.abs(diffY)) {
//                    if (diffX > 0) {
//                        mode = MODE_LEFT;
//                    } else {
//                        mode = MODE_RIGHT;
//                    }
//
//                    movePending = false;
//                }
//
//                if (Math.abs(diffY) > 10 && Math.abs(diffY) > 2 * Math.abs(diffX)) {
//                    if (diffY > 0) {
//                        mode = MODE_UP;
//                    } else {
//                        mode = MODE_DOWN;
//                    }
//
//                    movePending = false;
//                }
//
//                switch (mode) {
//                    case MODE_LEFT:
//                        goToLeft();
//                        dropNextNumber();
//                        break;
//
//                    case MODE_RIGHT:
//                        goToRight();
//                        dropNextNumber();
//                        break;
//
//                    case MODE_UP:
//                        goToUp();
//                        dropNextNumber();
//                        break;
//
//                    case MODE_DOWN:
//                        goToDown();
//                        dropNextNumber();
//                        break;
//                }
//                break;
//        }
//        return true;
//    }}