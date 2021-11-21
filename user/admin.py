from django.contrib import admin
from .models import User
from django.contrib.auth.models import Group

# Register your models here.
class UserAdmin(admin.ModelAdmin):
    list_display = (
        'userid',
        'username',
        'level',
        'date_joined'
    )
    search_fields = ('userid', 'username')

admin.site.register(User, UserAdmin) # Admin에서 User 등록
admin.site.unregister(Group)         # Admin에서 Group을 삭제
