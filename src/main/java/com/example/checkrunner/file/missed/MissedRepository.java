package com.example.checkrunner.file.missed;

import com.example.checkrunner.file.interf.Inputable;
import com.example.checkrunner.util.RequestUtil;

/**
 * ����� ���������� �� ���������� �����������
 */
public class MissedRepository implements Inputable {

    /**
     * ����� ������������ ������ ��� ���������� ������ �����
     */
    @Override
    public void inputInFile(RequestUtil request) {
        System.out.println("File not found");
    }
}
