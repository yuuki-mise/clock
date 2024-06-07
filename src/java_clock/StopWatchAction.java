package java_clock;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Java演習ストップウォッチ表示プログラム
 * 
 * @author Mise Yuki
 * @version 1.0.0
 * 
 */

public class StopWatchAction extends Object {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Timer aTimer;

    /**
     * StopWatchActionメソッド
     * ストップウォッチを表示する
     * 
     * @param なし
     * @return なし
     */
    public void perform() {

        // ウィンドウ（フレーム）を生成する
        JFrame StopWatchFrame = new JFrame("StopWatch");
        // 閉じるボタンを押したときに閉じる
        StopWatchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ウィンドウ（フレーム）のサイズを設定する
        StopWatchFrame.setSize(1200, 500);
        // ウィンドウ（フレーム）を真ん中に表示する
        StopWatchFrame.setLocationRelativeTo(null);
        StopWatchFrame.setLayout(new GridLayout(2, 1));

        // ラベルを追加する
        JLabel dateLabel1 = new JLabel();
        dateLabel1.setText("");
        dateLabel1.setForeground(Color.BLACK);
        // フォントのスタイルをPLAINに設定
        dateLabel1.setFont(new Font("Consolas", Font.PLAIN, 60));
        StopWatchFrame.add(dateLabel1);

        // ラベルを追加する
        JLabel dateLabel2 = new JLabel();
        dateLabel2.setText("");
        dateLabel2.setForeground(Color.BLACK);
        // フォントのスタイルをPLAINに設定
        dateLabel2.setFont(new Font("Consolas", Font.PLAIN, 60));
        StopWatchFrame.add(dateLabel2);

        // ボタンを追加する
        JButton startButton = new JButton("START");
        JButton stopButton = new JButton("STOP");

        stopButton.setEnabled(false);

        // startボタンを押したとき
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent anEvent) {
                startButton.setEnabled(false);

                // 秒数を表示する
                TimerTask calcProgressTimeTask = new TimerTask() {
                    public void run() {
                        LocalDateTime nowTime = LocalDateTime.now();
                        Duration ProgressTime = Duration.between(StopWatchAction.this.startTime, nowTime);
                        dateLabel1.setText(String.format("%02d:%02d:%02d.%03d", ProgressTime.toHours(),
                                ProgressTime.toMinutes() % 60, ProgressTime.toSeconds() % 60,
                                ProgressTime.toMillisPart()));
                    }
                };

                startTime = LocalDateTime.now();
                aTimer = new Timer();
                aTimer.scheduleAtFixedRate(calcProgressTimeTask, 0, 10);
                stopButton.setEnabled(true);
            }
        });
        StopWatchFrame.add(startButton);

        // stopボタンを押したとき
        stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent anEvent) {
                StopWatchAction.this.endTime = LocalDateTime.now();
                dateLabel1.setVisible(false);
                aTimer.cancel();
                aTimer = null;
                Duration ProgressTime = Duration.between(StopWatchAction.this.startTime, StopWatchAction.this.endTime);
                dateLabel1.setText(String.format("%02d:%02d:%02d.%03d", ProgressTime.toHours(),
                        ProgressTime.toMinutes() % 60, ProgressTime.toSeconds() % 60, ProgressTime.toMillisPart()));
                stopButton.setEnabled(false);
                startButton.setEnabled(true);
                dateLabel1.setVisible(true);
            }
        });
        StopWatchFrame.add(stopButton);

        StopWatchFrame.setVisible(true);
    }
}
