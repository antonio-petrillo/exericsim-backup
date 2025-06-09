;;; leap.el --- Leap exercise (exercism)  -*- lexical-binding: t; -*-

;;; Commentary:

(defun zero? (num)
  (= 0 num))

(defun leap-year-p (year)
  (and
   (zero? (mod year 4))
   (or
    (not (zero? (mod year 100)))
    (zero? (mod year 400)))))

(provide 'leap-year-p)
;;; leap.el ends here
