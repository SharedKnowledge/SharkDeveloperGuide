
class DrawHandler():
	def __init__(self, screen):
		self.screen 	=  screen
		self.observer 	= []

	def addObserver(self, observer):
		self.observer.append(observer)

	def reomveObserver(self, observer):
		self.observer.remove(observer)

	def drawObserver(self):
		for obs in self.observer:
			
			obs.draw(self.screen)