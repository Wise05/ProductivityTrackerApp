import matplotlib.pyplot as plt
import numpy as np

x_1 = np.linspace(0,5,50)
y_1 = x_1**2
plt.plot(x_1, y_1)
plt.title('Squared numbers chart')
plt.xlabel('Nums')
plt.ylabel('Squares')
plt.show()