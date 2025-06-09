;;; pangram.el --- Pangram (exercism)  -*- lexical-binding: t; -*-

;;; Commentary:

(defun is-pangram (phrase)
  (let* ((phrase (->> phrase (downcase) (replace-regexp-in-string "[^a-z]" "")))
         (charset '())
         (_ (dotimes (i (length phrase))
           (let ((char (aref phrase i)))
             (if (not (member char charset))
                 (setq charset (cons char charset)))
             ))))
    (= 26 (length charset))))


(provide 'pangram)
;;; pangram.el ends here
