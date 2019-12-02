import random

tests = 1000000

results = {'Loss':0,'SP1':0,'SP2':0,'MP':0,'LP1':0,'LP2':0,'GP':0}

numspins = 3

for i in range(tests):
    points = 0
    spins = 0 
    while(points >= 0 and spins < numspins):
        spin = random.randint(0,359)
        # print ('spin: %s' % spin)
        if(spin <= 5):
            points += 10
        elif(spin <= 115):
            points += 1
        elif(spin <= 175):
            points += 2
        elif(spin <= 190):
            points += 3
        elif(spin <= 240):
            points = -1
        elif(spin <= 320):
            points -= 3
        else:
            points -= 1
        spins += 1
        # print('points: %s' % points)
    if points < 0:
        results['Loss'] += 1
    elif points <= 3:
        results['SP1'] += 1
    elif points <= 5:
        results['SP2'] += 1
    elif points <= 10:
        results['MP'] += 1
    elif points <= 20:
        results['LP1'] += 1
    elif points <= 29:
        results['LP2'] += 1
    else:
        results['GP'] += 1
    # print()

for k in results.keys():#sorted(results.keys()):
    print('%s %s%% of the time.' % (k, 100 * results.get(k)/tests))
