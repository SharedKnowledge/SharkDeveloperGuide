import pygame as pg
from Animation import Animation
from AnimationObserver import AnimationObserver

class AnimationHandler:
	def __init__ (self,fps, screen):
		self.screen 		= screen
		self.fps 			= fps
		self.observers 		= []

	def addObserver(self, observer):
		self.observers.append(observer)
		observer.seedFps(self.fps)
		

	def notifyObserver(self, currentFps):
		for observer in self.observers:
			observer.notify(currentFps)
		