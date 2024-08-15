import matplotlib.pyplot as plt
import numpy as np
import datetime

from matplotlib.ticker import MaxNLocator

now = datetime.datetime.now()

# The rest of this file creates 18 different plots based on data collected
# I didn't care too much to use functions/classes ... so sorry about readability
# Also, its pretty slow

dayInMinAxis = {
    # creates time in hours:min for the past 24 hours
    "minutesAxis": [
        f"{(now - datetime.timedelta(minutes=(24 * 60 - i - 1))).hour}:{(now - datetime.timedelta(minutes=(24 * 60 - i - 1))).minute:02d}"
        for i in range(24 * 60)],
    "enters": [0 for i in range(24 * 60)],
    "spaces": [0 for i in range(24 * 60)],
    "clicks": [0 for i in range(24 * 60)]
}
weekInHoursAxis = {
    # creates time in hours:00 for the past week
    "hoursAxis": [
        f"{(now - datetime.timedelta(hours=(7 * 24 - i - 1))).day}, {(now - datetime.timedelta(hours=(7 * 24 - i - 1))).hour}:00"
        for i in range(7 * 24)],
    "enters": [0 for i in range(7 * 24)],
    "spaces": [0 for i in range(7 * 24)],
    "clicks": [0 for i in range(7 * 24)]
}
monthInDaysAxis = {
    # creates time in month/days (USA!) for the past 30 days
    "daysAxis": [
        f"{(now - datetime.timedelta(days=(30 - i - 1))).month}/{(now - datetime.timedelta(days=(30 - i - 1))).day}" for
        i in range(30)],
    "enters": [0 for i in range(30)],
    "spaces": [0 for i in range(30)],
    "clicks": [0 for i in range(30)]
}

# iterates through file of the past 30 days and update each value accordingly
for _ in range(30):
    day = now - datetime.timedelta(days=(30 - _ - 1))
    fileName = f"{day.year} {day.month} {day.day}"

    try:
        with open(f"C:/Users/zevan/IdeaProjects/Productivity Tracker App/src/main/data_logs/{fileName}.txt") as f:
            for line in f:
                try:
                    buffer = line.split(",")

                    if now == day:
                        for i in range(len(dayInMinAxis["minutesAxis"])):
                            if f"{buffer[0]}:{int(buffer[1]):02d}" == dayInMinAxis["minutesAxis"][i]:
                                dayInMinAxis["enters"][i] = int(buffer[2])
                                dayInMinAxis["spaces"][i] = int(buffer[3])
                                dayInMinAxis["clicks"][i] = int(buffer[4])

                    if now - datetime.timedelta(days=7) <= day:
                        for i in range(len(weekInHoursAxis["hoursAxis"])):
                            if f"{day.day}, {buffer[0]}:00" == weekInHoursAxis["hoursAxis"][i]:
                                weekInHoursAxis["enters"][i] += int(buffer[2])
                                weekInHoursAxis["spaces"][i] += int(buffer[3])
                                weekInHoursAxis["clicks"][i] += int(buffer[4])

                    for i in range(len(monthInDaysAxis["daysAxis"])):
                        if f"{day.month}/{day.day}" == monthInDaysAxis["daysAxis"][i]:
                            monthInDaysAxis["enters"][i] += int(buffer[2])
                            monthInDaysAxis["spaces"][i] += int(buffer[3])
                            monthInDaysAxis["clicks"][i] += int(buffer[4])

                except Exception as e:
                    print(f"error processing line: {e}")
    except:
        # program was not run on day so
        nothing = "happens"


def createGraph(x_axes, y_axes, dataType, fileName, cum):
    fig = plt.figure(figsize=(5, 4))
    a = fig.add_axes([0.1, 0.1, 0.9, 0.9])
    a.set_xlabel("Time")
    a.set_ylabel(dataType)
    a.set_title(fileName)

    if cum == True:
        y_axes = np.cumsum(y_axes)

    a.plot(x_axes, y_axes, label=f"Time v. {dataType}")
    a.xaxis.set_major_locator(MaxNLocator(nbins=7))
    fig.savefig(f"C:/Users/zevan/IdeaProjects/Productivity Tracker App/src/main/graphs/{fileName}")
    plt.close(fig)


# Day graphs
createGraph(dayInMinAxis["minutesAxis"], dayInMinAxis["enters"], "enters", "Enters in day", False)
createGraph(dayInMinAxis["minutesAxis"], dayInMinAxis["enters"], "enters", "Cum Enters in day", True)

createGraph(dayInMinAxis["minutesAxis"], dayInMinAxis["spaces"], "spaces", "Spaces in day", False)
createGraph(dayInMinAxis["minutesAxis"], dayInMinAxis["spaces"], "spaces", "Cum Spaces in day", True)

createGraph(dayInMinAxis["minutesAxis"], dayInMinAxis["clicks"], "clicks", "Clicks in day", False)
createGraph(dayInMinAxis["minutesAxis"], dayInMinAxis["clicks"], "clicks", "Cum Clicks in day", True)

# Week graphs
createGraph(weekInHoursAxis["hoursAxis"], weekInHoursAxis["enters"], "enters", "Enters in week", False)
createGraph(weekInHoursAxis["hoursAxis"], weekInHoursAxis["enters"], "enters", "Cum Enters in week", True)

createGraph(weekInHoursAxis["hoursAxis"], weekInHoursAxis["spaces"], "spaces", "Spaces in week", False)
createGraph(weekInHoursAxis["hoursAxis"], weekInHoursAxis["spaces"], "spaces", "Cum Spaces in week", True)

createGraph(weekInHoursAxis["hoursAxis"], weekInHoursAxis["clicks"], "clicks", "Clicks in week", False)
createGraph(weekInHoursAxis["hoursAxis"], weekInHoursAxis["clicks"], "clicks", "Cum Clicks in week", True)

# Month graphs
createGraph(monthInDaysAxis["daysAxis"], monthInDaysAxis["enters"], "enters", "Enters in month", False)
createGraph(monthInDaysAxis["daysAxis"], monthInDaysAxis["enters"], "enters", "Cum Enters in month", True)

createGraph(monthInDaysAxis["daysAxis"], monthInDaysAxis["spaces"], "spaces", "Spaces in month", False)
createGraph(monthInDaysAxis["daysAxis"], monthInDaysAxis["spaces"], "spaces", "Cum Spaces in month", True)

createGraph(monthInDaysAxis["daysAxis"], monthInDaysAxis["clicks"], "clicks", "Clicks in month", False)
createGraph(monthInDaysAxis["daysAxis"], monthInDaysAxis["clicks"], "clicks", "Cum Clicks in month", True)