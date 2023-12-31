//package com.lotta.itunesapi.fake
//
//import com.lotta.itunesapi.interfaces.MediaRoomDatabaseInterface
//import com.lotta.itunesapi.room.DaoDatabase
//import com.lotta.itunesapi.model.Track
//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
//import io.reactivex.rxjava3.core.Completable
//import io.reactivex.rxjava3.core.Flowable
//import io.reactivex.rxjava3.schedulers.Schedulers
//
//class FakeDataManager(private val database: DaoDatabase) : MediaRoomDatabaseInterface {
//    override fun getBookmarkByID(trackId: Int): Flowable<Track> {
//        return database.favoritesDao().get(trackId)
//    }
//
//    override fun insertFavorite(model: Track): Completable {
//        return Completable.fromAction {
//            database.favoritesDao().insert(model)
//        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//    }
//
//    override fun updateFavorite(model: Track): Completable {
//        return Completable.fromAction {
//            database.favoritesDao().update(model)
//        }
//    }
//
//    override fun deleteFavorite(model: Track): Completable {
//        return Completable.fromAction {
//            database.favoritesDao().delete(model)
//        }
//    }
//
//    override fun deleteAllFavorite() {
//        Completable.fromAction {
//            database.favoritesDao().deleteAll()
//        }.subscribeOn(Schedulers.io()).subscribe()
//    }
//
//    override fun getAllFavorite(): Flowable<List<Track>> {
//        return database.favoritesDao().getAll()
//    }
//}