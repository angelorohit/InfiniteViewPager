InfiniteViewPager
=================

An Android ViewPager that supports wrap-around of fragments.


![imgur](http://i.imgur.com/3quYzYl.gif)

## The Problem
A regular ViewPager in Android does not support carousel-like scrolling of its items. We can scroll from the first item to the last item but not directly from the last item back to the first item and vice-versa. The InfiniteViewPager solves this issue with minimal code and no hacks.

## The Source
- **InfiniteViewPager** provides its own OnPageChangeListener. This listener performs circular rotation of the fragments in the ViewPager's adapter whenever a new page is selected.

- **InfiniteViewPagerAdapter** simply stores a List of Fragments for the InfiniteViewPager.

## Constraints
Wrap-around functionality only works if there are 3 or more fragments in the InfiniteViewPager. If the ViewPager has less than 3 fragments in it, the user can still swipe back and forth between the existing fragments just as in a normal ViewPager.
