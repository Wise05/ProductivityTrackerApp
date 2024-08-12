import matplotlib.pyplot as plt
import datetime

# This file creates 18 different plots based on data collected
# I didn't care too much to use functions/classes ... so sorry about readability

now = datetime.datetime.now()

dayInMinAxis = {
    # creates time in hours:min for the past 24 hours
    "minutesAxis":
        [f"{(now - datetime.timedelta(minutes=(24 * 60 - i - 1))).hour}:{(now - datetime.timedelta(minutes=(24 * 60 - i - 1))).minute:02d}" for i in range(24 * 60)],
    "enters": [0 for i in range(24 * 60)],
    "spaces": [0 for i in range(24 * 60)],
    "clicks": [0 for i in range(24 * 60)]
}
weekInHoursAxis = {
    # creates time in hours:min
    "hoursAxis":
        [f"{(now - datetime.timedelta(hours=(7 * 24 - i - 1))).day}, {(now - datetime.timedelta(hours=(7 * 24 - i - 1))).hour}:00" for i in range(7 * 24)],
    "enters": [0 for i in range(7 * 24)],
    "spaces": [0 for i in range(7 * 24)],
    "clicks": [0 for i in range(7 * 24)]
}
monthInDaysAxis = {
    "daysAxis": [f"{(now - datetime.timedelta(days=(30 - i - 1))).month}/{(now - datetime.timedelta(days=(30 - i - 1))).day:02d}" for i in range(30)],
    "enters": [0 for i in range(30)],
    "spaces": [],
    "clicks": []
}

# TODO
# Create time axis beforehand

for i in range(30):
    day = now - datetime.timedelta(days=(30 - i - 1))
    fileName = f"{day.year} {day.month} {day.day}"

    try:
        with open(f"C:/Users/zevan/IdeaProjects/Productivity Tracker App/src/main/data_logs/{fileName}.txt") as f:
            for line in f:
                try:
                    buffer = line.split(",")

                    if now == day:
                        for i in range(len(dayInMinAxis["minutesAxis"])):
                            if f"{buffer[0]}:{buffer[1]:02d}" == dayInMinAxis["minutesAxis"][i]:
                                dayInMinAxis["enters"][i] = int(buffer[2])
                                dayInMinAxis["spaces"][i] = int(buffer[3])
                                dayInMinAxis["clicks"][i] = int(buffer[4])

                    if now - datetime.timedelta(days=7) <= day:
                        for i in range(len(weekInHoursAxis["hoursAxis"])):
                            if f"{day.day}, {buffer[0]}:00" == weekInHoursAxis["hoursAxis"][i]:
                                weekInHoursAxis["enters"][i] += int(buffer[2])
                                weekInHoursAxis["spaces"][i] += int(buffer[3])
                                weekInHoursAxis["clicks"][i] += int(buffer[4])

                except Exception as e:
                    print(f"error processing line: {e}")
                # add hour to array for week

                # always add day to month array

    except:
        nothing = "happens"
print(weekInHoursAxis["hoursAxis"])
print(weekInHoursAxis["clicks"])
