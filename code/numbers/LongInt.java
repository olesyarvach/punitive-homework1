 package code.numbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Только неотрицательные числа поддерживаются
 */
public class LongInt implements Cloneable {
    private List<Integer> lnum; // представление числа по разрядам: base^0, base^1, ...
    private final int base = 1000 * 1000 * 1000; // основание СИ = 10^n

    private LongInt(int size) {
        lnum = new ArrayList<>(Collections.nCopies(size, 0));
    }

    private LongInt(List<Integer> lnum) {
        lnum = new ArrayList<>(lnum);
    }

    public LongInt(String number) {
        lnum = new ArrayList<>();
        for (int i = number.length(); i > 0; i -= Integer.numberOfTrailingZeros(base)) {
            if (i < 9) {
                lnum.add(Integer.parseInt(number.substring(0, i)));
            } else {
                lnum.add(Integer.parseInt(number.substring(i - 9, i)));
            }
        }
    }

    private void printDigit(int idx, boolean isFirst) {
        int current = lnum.get(idx);
        if (!isFirst) {
            int countOfDecimalDigits = Integer.toString(current).length();

            for (int i = 0; i < Integer.numberOfTrailingZeros(base) - countOfDecimalDigits; ++i) {
                System.out.print(0);
            }
        }
        System.out.print(current);
    }

    public LongInt add(LongInt other) {
        LongInt clone = (LongInt) this.clone();
        int carry = 0;
        for (int i = 0; i < Math.max(clone.lnum.size(), other.lnum.size()) || carry != 0; ++i) {
            if (i == clone.lnum.size()) {
                clone.lnum.add(0);
            }

            int val = carry;
            if (i < other.sizeOfNum()) {
                val += other.getDigit(i);
            }
            clone.addToDigit(i, val);

            if (clone.getDigit(i) >= base) {
                carry = 1;
            } else {
                carry = 0;
            }

            if (carry == 1) {
                clone.subtractFromDigit(i, base);
            }
        }

        return clone;
    }

    private void clean() {
        while (sizeOfNum() > 1 && getDigit(sizeOfNum() - 1) == 0) {
            lnum.remove(sizeOfNum() - 1);
        }
    }

    public LongInt mul(LongInt other) {
        LongInt num = new LongInt(sizeOfNum() + other.sizeOfNum());
        for (int i = 0; i < sizeOfNum(); ++i) {
            int carry = 0;
            for (int j = 0; j < other.sizeOfNum() || carry != 0; ++j) {
                long current = num.getDigit(i + j) + (long) getDigit(i) * (j < other.sizeOfNum() ? other.getDigit(j) : 0) + carry;
                num.setDigit(i + j, (int) (current % base));
                carry = (int) (current / base);
            }
        }
        num.clean();
        return num;
    }

    public LongInt div(int other) {
        LongInt num = (LongInt) clone();
        int carry = 0;
        for (int i = num.sizeOfNum() - 1; i >= 0; --i) {
            long current = num.getDigit(i) + (long) carry * base;
            num.setDigit(i, (int) (current / other));
            carry = (int) (current % other);
        }
        num.clean();
        return num;
    }

    private void setDigit(int idx, int digit) {
        lnum.set(idx, digit);
    }

    public LongInt sub(LongInt other) {
        LongInt clone = (LongInt) this.clone();
        int carry = 0;
        for (int i = 0; i < other.lnum.size() || carry != 0; ++i) {
            int val = carry;
            if (i < other.sizeOfNum()) {
                val += other.getDigit(i);
            }
            clone.subtractFromDigit(i, val);

            if (clone.getDigit(i) < 0) {
                carry = 1;
            } else {
                carry = 0;
            }

            if (carry == 1) {
                clone.addToDigit(i, base);
            }
        }

        clone.clean();

        return clone;
    }

    private int sizeOfNum() {
        return lnum.size();
    }

    private int getDigit(int idx) {
        return lnum.get(idx);
    }

    private void addToDigit(int idx, int val) {
        int before = getDigit(idx);
        setDigit(idx, before + val);
    }

    private void subtractFromDigit(int idx, int val) {
        addToDigit(idx, -val);
    }

    @Override
    protected Object clone() {
        return new LongInt(lnum);
    }

    public void print() {
        if (lnum.isEmpty()) {
            System.out.println(0);
            return;
        }
        printDigit(lnum.size() - 1, true);
        for (int i = lnum.size() - 2; i >= 0; --i) {
            printDigit(i, false);
        }
        System.out.println();
    }
}
