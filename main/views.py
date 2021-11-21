from django.shortcuts import render, redirect
from .forms import UnprocessedForm
from .models import Unprocessed
from django.contrib import messages

# Create your views here.
def index(request):
    return render(request, 'main/index.html')

def unprocessed(request):
    if request.method == "POST":
        form = UnprocessedForm(request.POST)
        if form.is_valid():
            # commit = False는 바로 저장하는 것을 방지
            unprocessed_form = form.save(commit=False)
            if request.POST.get('unprocessed_img', True):
                messages.success(request, "업로드 성공")
                unprocessed_form.unprocessed_img = request.FILES['unprocessed_img']
            unprocessed_form.save() # written
            return redirect('/unprocessed/')
        else:
            return redirect('/unprocessed/')

    else:
        form = UnprocessedForm()
        unprocessed_list = Unprocessed.objects.all()
        context = {
            'form': form,
            'unprocessed_list_' : unprocessed_list,
        }
        return render(request, 'main/unprocessed.html', context)

