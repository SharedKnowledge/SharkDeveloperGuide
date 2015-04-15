import pygame as pg

class Animation:

	def __init__ (self, fps, images):
		self.images = []
		self.current = 0
		self.fps = fps
		for img in images:
			self.images.append(pg.image.load(img))

	def addImg(self, imagePath):
		self.images.append( pg.image.load(imagePath))
		
	def update(self):
		self.current = (self.current + 1) % len(self.images)
		

	def draw(self, screen, x_pos, y_pos):
		screen.blit(self.images[self.current], (x_pos, y_pos))

	def notify(self, currentFps):
		if (currentFps % self.fpsToken) == 0:
			self.update()
			
	def getFps(self):
		return self.fps

	def calcToken(self, fps):
		self.fpsToken =  round(fps / self.fps)
