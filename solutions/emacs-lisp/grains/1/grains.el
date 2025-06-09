;;; grains.el --- Grains exercise (exercism)  -*- lexical-binding: t; -*-

;;; Commentary:

(require 'calc)

(defun square (n)
  (ash 1 (- n 1)))

(defun total ()
  (- (square 65) 1))

(provide 'grains)
;;; grains.el ends here
