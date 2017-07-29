package br.com.betoalves.carcrud.utils;

import android.util.Log;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import br.com.betoalves.carcrud.annotations.Profile;
import br.com.betoalves.carcrud.mvp.impl.CarFormPresenter;
import br.com.betoalves.carcrud.mvp.impl.MainActivityPresenter;
import br.com.betoalves.carcrud.repository.implementation.BrandRepository;
import br.com.betoalves.carcrud.repository.implementation.CarRepository;
import br.com.betoalves.carcrud.repository.implementation.TypeRepository;
import br.com.betoalves.carcrud.repository.interfaces.IBrandRepository;
import br.com.betoalves.carcrud.repository.interfaces.ICarRepository;
import br.com.betoalves.carcrud.repository.interfaces.ITypeRepository;

import static br.com.betoalves.carcrud.mvp.CarFormMvp.ICarFormPresenter;
import static br.com.betoalves.carcrud.mvp.MainActivityMvp.IMainActivityPresenter;


public final class DependencyCacheHelper {

    private static final String TAG = DependencyCacheHelper.class.getSimpleName();

    private static Map<Class, Set<Object>> dependencyCache = new HashMap<>();
    private static Map<Class, Set<Class>> dependencyLinks = new HashMap<>();

    static {

        link(IBrandRepository.class,BrandRepository.class);
        link(ICarRepository.class,CarRepository.class);
        link(ITypeRepository.class,TypeRepository.class);

        link(ICarFormPresenter.class, CarFormPresenter.class);
        link(IMainActivityPresenter.class, MainActivityPresenter.class);
    }

    public static <T> void disposeInstance(Class<T> tClass){
        dependencyCache.remove(tClass);
    }

    public static Map<Class, Set<Class>> getDependencyLinks() {
        return dependencyLinks;
    }

    public static <T> T getInstance(Class<T> tClass){
        return getInstance(tClass, null);
    }

    public static <T> T getInstance(Class<T> tClass, String profile){
        T value = null;
        if(dependencyCache.containsKey(tClass)){
            Set<Object> implementationSet = dependencyCache.get(tClass);
            Object valueToCast = null;
            Class valueClass = getClassBasedOnProfile(dependencyLinks.get(tClass), profile);
            Iterator valuesIterator = implementationSet.iterator();
            while(valuesIterator.hasNext()){
                Object _value = valuesIterator.next();
                if(_value.getClass().equals(valueClass)){
                    valueToCast = _value;
                    break;
                }
            }
            if(valueToCast != null) {
                value = tClass.cast(valueToCast);
            }
            else {
                value = instantiateNew(tClass, profile);
            }
        }else if(dependencyLinks.containsKey(tClass)){
            value = instantiateNew(tClass, profile);
        }
        return value;
    }

    private static <T> T instantiateNew(Class<T> tClass, String profile){
        T value = null;
        Set<Class> classSet = dependencyLinks.get(tClass);
        //Entre as várias implementações de uma interface, tenta encontrar a que bate com o profile
        Class valueClass = getClassBasedOnProfile(classSet, profile);
        if(valueClass == null){
            Log.e(TAG, "No implementation of " + tClass.getName() + " matches profile : " + profile);
        }
        else
        {
            try {
                value = tClass.cast(valueClass.newInstance());
                if(!dependencyCache.containsKey(tClass)){
                    dependencyCache.put(tClass, new HashSet<>());
                }
                dependencyCache.get(tClass).add(value);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
        return value;
    }

    private static Class getClassBasedOnProfile(Set<Class> classSet, String profile){
        Class valueClass = null;
        if(classSet.size() > 1){
            if(StringUtils.isNotNullOrEmpty(profile)){
                Iterator<Class> classIterator = classSet.iterator();
                while (classIterator.hasNext()){
                    Class candidate = classIterator.next();
                    if(candidate.isAnnotationPresent(Profile.class)){
                        Profile annotaion = (Profile) candidate.getAnnotation(Profile.class);
                        String[] profiles = annotaion.profiles();
                        for (String _profile: profiles) {
                            if(_profile.equals(profile)){
                                valueClass = candidate;
                                break;
                            }
                        }
                    }
                    if(valueClass != null){
                        break;
                    }
                }
            }else {
                throw new IllegalArgumentException("If an interface has more than one implementation, a profile must be informed");
            }
        } else if(classSet.size() > 0) {
            valueClass = classSet.iterator().next();
        }
        return valueClass;
    }

    public static void link(Class _interface, Class _implementation){
        if(!dependencyLinks.containsKey(_interface)){
            dependencyLinks.put(_interface, new HashSet<Class>());
        }
        dependencyLinks.get(_interface).add(_implementation);
    }
}
