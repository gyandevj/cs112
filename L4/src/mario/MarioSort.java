package mario;

public class MarioSort {
    private Player[] marioParty;
    private Racer[] marioKart;

    public void sortMarioKart() {
        if (marioKart == null || marioKart.length == 0)
            return;
        for (int i = 0; i < marioKart.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < marioKart.length; j++) {
                if (marioKart[j].getAverage() < marioKart[minIdx].getAverage()) {
                    minIdx = j;
                }
            }
            Racer temp = marioKart[minIdx];
            marioKart[minIdx] = marioKart[i];
            marioKart[i] = temp;
        }
    }

    public void mergeHelper(Player arr[], int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            mergeHelper(arr, left, middle);
            mergeHelper(arr, middle + 1, right);
            merge(arr, left, middle, right);
        }
    }

    public void sortMarioParty() {
        if (marioParty != null && marioParty.length > 0) {
            mergeHelper(marioParty, 0, marioParty.length - 1);
        }
    }

    public void merge(Player arr[], int left, int middle, int right) {
        int size1 = middle - left + 1;
        int size2 = right - middle;
        Player newL[] = new Player[size1];
        Player newR[] = new Player[size2];
        for (int i = 0; i < size1; ++i) {
            newL[i] = arr[left + i];
        }
        for (int j = 0; j < size2; ++j) {
            newR[j] = arr[middle + 1 + j];
        }
        int i = 0, j = 0;
        int k = left;
        while (i < size1 && j < size2) {
            if (newL[i].getStars() > newR[j].getStars()) {
                arr[k] = newL[i];
                i++;
            } else if (newL[i].getStars() < newR[j].getStars()) {
                arr[k] = newR[j];
                j++;
            } else {
                if (newL[i].getCoins() >= newR[j].getCoins()) {
                    arr[k] = newL[i];
                    i++;
                } else {
                    arr[k] = newR[j];
                    j++;
                }
            }
            k++;
        }
        while (i < size1) {
            arr[k] = newL[i];
            i++;
            k++;
        }
        while (j < size2) {
            arr[k] = newR[j];
            j++;
            k++;
        }
    }

    public void readData(String filename) {
        StdIn.setFile(filename);
        String[] lines = StdIn.readAllLines();
        if (filename.toLowerCase().contains("kart")) {
            marioKart = new Racer[lines.length - 1];
        } else if (filename.toLowerCase().contains("party")) {
            marioParty = new Player[lines.length - 1];
        }
        for (int i = 1; i < lines.length; i++) {
            String[] split = lines[i].split(",");
            if (filename.toLowerCase().contains("kart")) {
                int[] times = new int[] {
                        Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]) };
                marioKart[i - 1] = new Racer(split[0], times);
            } else if (filename.toLowerCase().contains("party")) {
                marioParty[i - 1] = new Player(split[0], Integer.parseInt(split[1]), Integer.parseInt(split[2]));
            }
        }
    }

    public Racer[] getMarioKart() {
        return marioKart;
    }

    public Player[] getMarioParty() {
        return marioParty;
    }
}
