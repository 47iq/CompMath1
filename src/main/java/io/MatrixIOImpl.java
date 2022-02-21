package io;

import data.InputDTO;
import data.MatrixImpl;

import java.io.*;
import java.util.ResourceBundle;
import java.util.stream.Stream;

public class MatrixIOImpl implements MatrixIO{

    private IOMode ioMode;

    private final int MAX_NUM = 20;

    private BufferedReader reader;

    private ResourceBundle bundle = ResourceBundle.getBundle("messages");

    @Override
    public InputDTO readMatrix() throws IOException {
        System.out.println(bundle.getString("FILE_OR_CONSOLE"));
        reader = new BufferedReader(new InputStreamReader(System.in));
        ioMode = IOMode.get(reader.readLine());
        if(ioMode.equals(IOMode.FILE)) {
            System.out.println(bundle.getString("FILE_NAME"));
            String fileName = reader.readLine();
            try {
                reader = new BufferedReader(new FileReader(fileName));
            } catch (Exception e) {
                throw new IOException(bundle.getString("ERR_FILE") + " " + fileName);
            }
        } else {
            System.out.println(bundle.getString("INPUT_COUNT"));
        }
        int num;
        try {
            num = Integer.parseInt(reader.readLine());
        } catch (Exception e) {
            throw new IOException(bundle.getString("ERR_NUMBER"));
        }
        if(num > MAX_NUM)
            throw new IOException(bundle.getString("ERR_BIG_NUMBER") + " " + MAX_NUM);
        if(num <= 0)
            throw new IOException(bundle.getString("ERR_LE_THAN_ZERO_NUMBER"));
        double inaccuracy;
        if(ioMode == IOMode.CONSOLE)
            System.out.println(bundle.getString("INPUT_INACCURACY"));
        try {
            inaccuracy = Double.parseDouble(reader.readLine());
        } catch (Exception e) {
            throw new IOException(bundle.getString("ERR_INACCURACY"));
        }
        if(inaccuracy <= 0)
            throw new IOException(bundle.getString("ERR_LE_THAN_ZERO_INACC"));
        if(ioMode == IOMode.CONSOLE)
            System.out.println(bundle.getString("INPUT_MATRIX"));
        double[][] contents = new double[num][num + 1];
        for (int i = 0; i < num; i++) {
            contents[i] = Stream.of(reader.readLine().split(" "))
                    .mapToDouble(Double::parseDouble)
                    .toArray();
        }
        return new InputDTO(inaccuracy, new MatrixImpl(contents));
    }

    @Override
    public void printAnswer(double[] contents, double[] inaccuracies, int iterationCount, int max_iters) {
        if(iterationCount == max_iters)
            System.out.println("Заданная погрешность не достигнута за максимальное заданное число итераций: " + max_iters);
        for (int i = 0; i < contents.length; i++)
            System.out.println("x" + (i + 1) + "= " + contents[i] + " | погрешность " + inaccuracies[i]);
        System.out.println("Количество итераций: " + iterationCount);
    }
}
