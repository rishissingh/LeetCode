class Solution {
    public int mostBooked(int n, int[][] meetings) {
         // Sort meetings by start time
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        // Min-heap for available rooms
        PriorityQueue<Integer> available = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            available.offer(i);
        }

        // Min-heap for busy rooms: {endTime, roomNumber}
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> a[0] == b[0]
                ? Long.compare(a[1], b[1])
                : Long.compare(a[0], b[0])
        );

        int[] count = new int[n];

        for (int[] meeting : meetings) {
            long start = meeting[0];
            long end = meeting[1];
            long duration = end - start;

            // Free rooms that are done before current meeting
            while (!busy.isEmpty() && busy.peek()[0] <= start) {
                available.offer((int) busy.poll()[1]);
            }

            int room;
            long newEnd;

            if (!available.isEmpty()) {
                // Assign immediately
                room = available.poll();
                newEnd = end;
            } else {
                // Delay meeting
                long[] earliest = busy.poll();
                room = (int) earliest[1];
                newEnd = earliest[0] + duration;
            }

            busy.offer(new long[]{newEnd, room});
            count[room]++;
        }

        // Find room with max meetings
        int maxMeetings = 0;
        int resultRoom = 0;

        for (int i = 0; i < n; i++) {
            if (count[i] > maxMeetings) {
                maxMeetings = count[i];
                resultRoom = i;
            }
        }

        return resultRoom;
    }
}