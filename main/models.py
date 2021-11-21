from django.db import models

# Create your models here.
class Unprocessed(models.Model):
    unprocessed_img = models.ImageField(
        blank=True,  # 비어도 ok
        null=True,  # null ok
        upload_to="unprocessed/"  # 경로 설정
    )
    content = models.TextField(verbose_name='content')

    def __str__(self):
        return self.title

    class Meta:
        db_table = 'unprocessed'
        verbose_name = 'Unprocessed'
        verbose_name_plural = 'Unprocessed'