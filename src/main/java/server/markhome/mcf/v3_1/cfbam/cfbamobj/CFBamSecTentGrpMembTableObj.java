// Description: Java 25 Table Object implementation for SecTentGrpMemb.

/*
 *	server.markhome.mcf.CFBam
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal CFBam 3.1 Business Application Model
 *	
 *	Copyright 2016-2026 Mark Stephen Sobkow
 *	
 *	This file is part of Mark's Code Fractal CFBam.
 *	
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later with classpath and static linking exceptions.
 *	
 *	As a special exception, Mark Sobkow gives you permission to link this library
 *	with independent modules to produce an executable, provided that none of them
 *	conflict with the intent of the GPLv3; that is, you are not allowed to invoke
 *	the methods of this library from non-GPLv3-compatibly licensed code. You may not
 *	implement an LPGLv3 "wedge" to try to bypass this restriction. That said, code which
 *	does not rely on this library is free to specify whatever license its authors decide
 *	to use. Mark Sobkow specifically rejects the infectious nature of the GPLv3, and
 *	considers the mere act of including GPLv3 modules in an executable to be perfectly
 *	reasonable given tools like modern Java's single-jar deployment options.
 *	
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *	
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *	
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *	
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 */

package server.markhome.mcf.v3_1.cfbam.cfbamobj;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;

public class CFBamSecTentGrpMembTableObj
	implements ICFBamSecTentGrpMembTableObj
{
	protected ICFBamSchemaObj schema;
	private Map<ICFSecSecTentGrpMembPKey, ICFSecSecTentGrpMembObj> members;
	private Map<ICFSecSecTentGrpMembPKey, ICFSecSecTentGrpMembObj> allSecTentGrpMemb;
	private Map< ICFSecSecTentGrpMembByTentGrpIdxKey,
		Map<ICFSecSecTentGrpMembPKey, ICFSecSecTentGrpMembObj > > indexByTentGrpIdx;
	private Map< ICFSecSecTentGrpMembByUserIdxKey,
		Map<ICFSecSecTentGrpMembPKey, ICFSecSecTentGrpMembObj > > indexByUserIdx;
	public static String TABLE_NAME = "SecTentGrpMemb";
	public static String TABLE_DBNAME = "sectentgrpmemb";

	public CFBamSecTentGrpMembTableObj() {
		schema = null;
		members = new HashMap<ICFSecSecTentGrpMembPKey, ICFSecSecTentGrpMembObj>();
		allSecTentGrpMemb = null;
		indexByTentGrpIdx = null;
		indexByUserIdx = null;
	}

	public CFBamSecTentGrpMembTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<ICFSecSecTentGrpMembPKey, ICFSecSecTentGrpMembObj>();
		allSecTentGrpMemb = null;
		indexByTentGrpIdx = null;
		indexByUserIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecSecTentGrpMembTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( CFSecSecTentGrpMembTableObj.getBackingClassCode() );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( CFSecSecTentGrpMembTableObj.getRuntimeClassCode() );
	}

	@Override
	public ICFSecSchemaObj getSchema() {
		return( schema );
	}

	@Override
	public void setSchema( ICFSecSchemaObj value ) {
		schema = (ICFBamSchemaObj)value;
	}

	@Override
	public String getTableName() {
		return( TABLE_NAME );
	}

	@Override
	public String getTableDbName() {
		return( TABLE_DBNAME );
	}

	@Override
	public Class getObjQualifyingClass() {
		return( null );
	}


	@Override
	public void minimizeMemory() {
		allSecTentGrpMemb = null;
		indexByTentGrpIdx = null;
		indexByUserIdx = null;
		List<ICFSecSecTentGrpMembObj> toForget = new LinkedList<ICFSecSecTentGrpMembObj>();
		ICFSecSecTentGrpMembObj cur = null;
		Iterator<ICFSecSecTentGrpMembObj> iter = members.values().iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			toForget.add( cur );
		}
		iter = toForget.iterator();
		while( iter.hasNext() ) {
			cur = iter.next();
			cur.forget();
		}
	}
	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamSecTentGrpMembObj.
	 */
	@Override
	public ICFSecSecTentGrpMembObj newInstance() {
		ICFSecSecTentGrpMembObj inst = new CFBamSecTentGrpMembObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamSecTentGrpMembObj.
	 */
	@Override
	public ICFSecSecTentGrpMembEditObj newEditInstance( ICFSecSecTentGrpMembObj orig ) {
		ICFSecSecTentGrpMembEditObj edit = new CFBamSecTentGrpMembEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecSecTentGrpMembObj realiseSecTentGrpMemb( ICFSecSecTentGrpMembObj Obj ) {
		ICFSecSecTentGrpMembObj obj = Obj;
		ICFSecSecTentGrpMembPKey pkey = obj.getPKey();
		ICFSecSecTentGrpMembObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecSecTentGrpMembObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByTentGrpIdx != null ) {
				ICFSecSecTentGrpMembByTentGrpIdxKey keyTentGrpIdx =
					schema.getCFSecBackingStore().getFactorySecTentGrpMemb().newByTentGrpIdxKey();
				keyTentGrpIdx.setRequiredSecTentGrpId( keepObj.getRequiredSecTentGrpId() );
				Map<ICFSecSecTentGrpMembPKey, ICFSecSecTentGrpMembObj > mapTentGrpIdx = indexByTentGrpIdx.get( keyTentGrpIdx );
				if( mapTentGrpIdx != null ) {
					mapTentGrpIdx.remove( keepObj.getPKey() );
					if( mapTentGrpIdx.size() <= 0 ) {
						indexByTentGrpIdx.remove( keyTentGrpIdx );
					}
				}
			}

			if( indexByUserIdx != null ) {
				ICFSecSecTentGrpMembByUserIdxKey keyUserIdx =
					schema.getCFSecBackingStore().getFactorySecTentGrpMemb().newByUserIdxKey();
				keyUserIdx.setRequiredSecUserId( keepObj.getRequiredSecUserId() );
				Map<ICFSecSecTentGrpMembPKey, ICFSecSecTentGrpMembObj > mapUserIdx = indexByUserIdx.get( keyUserIdx );
				if( mapUserIdx != null ) {
					mapUserIdx.remove( keepObj.getPKey() );
					if( mapUserIdx.size() <= 0 ) {
						indexByUserIdx.remove( keyUserIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByTentGrpIdx != null ) {
				ICFSecSecTentGrpMembByTentGrpIdxKey keyTentGrpIdx =
					schema.getCFSecBackingStore().getFactorySecTentGrpMemb().newByTentGrpIdxKey();
				keyTentGrpIdx.setRequiredSecTentGrpId( keepObj.getRequiredSecTentGrpId() );
				Map<ICFSecSecTentGrpMembPKey, ICFSecSecTentGrpMembObj > mapTentGrpIdx = indexByTentGrpIdx.get( keyTentGrpIdx );
				if( mapTentGrpIdx != null ) {
					mapTentGrpIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUserIdx != null ) {
				ICFSecSecTentGrpMembByUserIdxKey keyUserIdx =
					schema.getCFSecBackingStore().getFactorySecTentGrpMemb().newByUserIdxKey();
				keyUserIdx.setRequiredSecUserId( keepObj.getRequiredSecUserId() );
				Map<ICFSecSecTentGrpMembPKey, ICFSecSecTentGrpMembObj > mapUserIdx = indexByUserIdx.get( keyUserIdx );
				if( mapUserIdx != null ) {
					mapUserIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allSecTentGrpMemb != null ) {
				allSecTentGrpMemb.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSecTentGrpMemb != null ) {
				allSecTentGrpMemb.put( keepObj.getPKey(), keepObj );
			}

			if( indexByTentGrpIdx != null ) {
				ICFSecSecTentGrpMembByTentGrpIdxKey keyTentGrpIdx =
					schema.getCFSecBackingStore().getFactorySecTentGrpMemb().newByTentGrpIdxKey();
				keyTentGrpIdx.setRequiredSecTentGrpId( keepObj.getRequiredSecTentGrpId() );
				Map<ICFSecSecTentGrpMembPKey, ICFSecSecTentGrpMembObj > mapTentGrpIdx = indexByTentGrpIdx.get( keyTentGrpIdx );
				if( mapTentGrpIdx != null ) {
					mapTentGrpIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByUserIdx != null ) {
				ICFSecSecTentGrpMembByUserIdxKey keyUserIdx =
					schema.getCFSecBackingStore().getFactorySecTentGrpMemb().newByUserIdxKey();
				keyUserIdx.setRequiredSecUserId( keepObj.getRequiredSecUserId() );
				Map<ICFSecSecTentGrpMembPKey, ICFSecSecTentGrpMembObj > mapUserIdx = indexByUserIdx.get( keyUserIdx );
				if( mapUserIdx != null ) {
					mapUserIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecSecTentGrpMembObj createSecTentGrpMemb( ICFSecSecTentGrpMembObj Obj ) {
		ICFSecSecTentGrpMembObj obj = Obj;
		ICFSecSecTentGrpMemb rec = obj.getSecTentGrpMembRec();
		schema.getCFSecBackingStore().getTableSecTentGrpMemb().createSecTentGrpMemb(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecSecTentGrpMembObj readSecTentGrpMemb( ICFSecSecTentGrpMembPKey pkey ) {
		return( readSecTentGrpMemb( pkey, false ) );
	}

	@Override
	public ICFSecSecTentGrpMembObj readSecTentGrpMemb( ICFSecSecTentGrpMembPKey pkey, boolean forceRead ) {
		ICFSecSecTentGrpMembObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecSecTentGrpMemb readRec = schema.getCFSecBackingStore().getTableSecTentGrpMemb().readDerivedByIdIdx( null,
						pkey.getRequiredSecTentGrpId(),
						pkey.getRequiredSecUserId() );
			if( readRec != null ) {
				obj = schema.getSecTentGrpMembTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecSecTentGrpMembObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecTentGrpMembObj readSecTentGrpMemb( CFLibDbKeyHash256 SecTentGrpId,
		CFLibDbKeyHash256 SecUserId ) {
		return( readSecTentGrpMemb( SecTentGrpId,
			SecUserId, false ) );
	}

	@Override
	public ICFSecSecTentGrpMembObj readSecTentGrpMemb( CFLibDbKeyHash256 SecTentGrpId,
		CFLibDbKeyHash256 SecUserId, boolean forceRead ) {
		ICFSecSecTentGrpMembObj obj = null;
		ICFSecSecTentGrpMemb readRec = schema.getCFSecBackingStore().getTableSecTentGrpMemb().readDerivedByIdIdx( null,
			SecTentGrpId,
			SecUserId );
		if( readRec != null ) {
				obj = schema.getSecTentGrpMembTableObj().newInstance();
			obj.setPKey( readRec.getPKey() );
			obj.setRec( readRec );
			obj = (ICFSecSecTentGrpMembObj)obj.realise();
		}
		return( obj );
	}

	@Override
	public ICFSecSecTentGrpMembObj readCachedSecTentGrpMemb( ICFSecSecTentGrpMembPKey pkey ) {
		ICFSecSecTentGrpMembObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSecTentGrpMemb( ICFSecSecTentGrpMembObj obj )
	{
		final String S_ProcName = "CFBamSecTentGrpMembTableObj.reallyDeepDisposeSecTentGrpMemb() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		ICFSecSecTentGrpMembPKey pkey = obj.getPKey();
		ICFSecSecTentGrpMembObj existing = readCachedSecTentGrpMemb( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecSecTentGrpMembByTentGrpIdxKey keyTentGrpIdx = schema.getCFSecBackingStore().getFactorySecTentGrpMemb().newByTentGrpIdxKey();
		keyTentGrpIdx.setRequiredSecTentGrpId( existing.getRequiredSecTentGrpId() );

		ICFSecSecTentGrpMembByUserIdxKey keyUserIdx = schema.getCFSecBackingStore().getFactorySecTentGrpMemb().newByUserIdxKey();
		keyUserIdx.setRequiredSecUserId( existing.getRequiredSecUserId() );



		if( indexByTentGrpIdx != null ) {
			if( indexByTentGrpIdx.containsKey( keyTentGrpIdx ) ) {
				indexByTentGrpIdx.get( keyTentGrpIdx ).remove( pkey );
				if( indexByTentGrpIdx.get( keyTentGrpIdx ).size() <= 0 ) {
					indexByTentGrpIdx.remove( keyTentGrpIdx );
				}
			}
		}

		if( indexByUserIdx != null ) {
			if( indexByUserIdx.containsKey( keyUserIdx ) ) {
				indexByUserIdx.get( keyUserIdx ).remove( pkey );
				if( indexByUserIdx.get( keyUserIdx ).size() <= 0 ) {
					indexByUserIdx.remove( keyUserIdx );
				}
			}
		}


	}
	@Override
	public void deepDisposeSecTentGrpMemb( ICFSecSecTentGrpMembPKey pkey ) {
		ICFSecSecTentGrpMembObj obj = readCachedSecTentGrpMemb( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecTentGrpMembObj lockSecTentGrpMemb( ICFSecSecTentGrpMembPKey pkey ) {
		ICFSecSecTentGrpMembObj locked = null;
		ICFSecSecTentGrpMemb lockRec = schema.getCFSecBackingStore().getTableSecTentGrpMemb().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getSecTentGrpMembTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecSecTentGrpMembObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSecTentGrpMemb", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecSecTentGrpMembObj> readAllSecTentGrpMemb() {
		return( readAllSecTentGrpMemb( false ) );
	}

	@Override
	public List<ICFSecSecTentGrpMembObj> readAllSecTentGrpMemb( boolean forceRead ) {
		final String S_ProcName = "readAllSecTentGrpMemb";
		if( ( allSecTentGrpMemb == null ) || forceRead ) {
			Map<ICFSecSecTentGrpMembPKey, ICFSecSecTentGrpMembObj> map = new HashMap<ICFSecSecTentGrpMembPKey,ICFSecSecTentGrpMembObj>();
			allSecTentGrpMemb = map;
			ICFSecSecTentGrpMemb[] recList = schema.getCFSecBackingStore().getTableSecTentGrpMemb().readAllDerived( null );
			ICFSecSecTentGrpMemb rec;
			ICFSecSecTentGrpMembObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecTentGrpMembObj realised = (ICFSecSecTentGrpMembObj)obj.realise();
			}
		}
		int len = allSecTentGrpMemb.size();
		ICFSecSecTentGrpMembObj arr[] = new ICFSecSecTentGrpMembObj[len];
		Iterator<ICFSecSecTentGrpMembObj> valIter = allSecTentGrpMemb.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFSecSecTentGrpMembObj> arrayList = new ArrayList<ICFSecSecTentGrpMembObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecTentGrpMembObj> cmp = new Comparator<ICFSecSecTentGrpMembObj>() {
			@Override
			public int compare( ICFSecSecTentGrpMembObj lhs, ICFSecSecTentGrpMembObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					ICFSecSecTentGrpMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecTentGrpMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecTentGrpMembObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecTentGrpMembObj> readCachedAllSecTentGrpMemb() {
		final String S_ProcName = "readCachedAllSecTentGrpMemb";
		ArrayList<ICFSecSecTentGrpMembObj> arrayList = new ArrayList<ICFSecSecTentGrpMembObj>();
		if( allSecTentGrpMemb != null ) {
			int len = allSecTentGrpMemb.size();
			ICFSecSecTentGrpMembObj arr[] = new ICFSecSecTentGrpMembObj[len];
			Iterator<ICFSecSecTentGrpMembObj> valIter = allSecTentGrpMemb.values().iterator();
			int idx = 0;
			while( ( idx < len ) && valIter.hasNext() ) {
				arr[idx++] = valIter.next();
			}
			if( idx < len ) {
				throw new CFLibArgumentUnderflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
			}
			else if( valIter.hasNext() ) {
				throw new CFLibArgumentOverflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
			}
			for( idx = 0; idx < len; idx ++ ) {
				arrayList.add( arr[idx] );
			}
		}
		Comparator<ICFSecSecTentGrpMembObj> cmp = new Comparator<ICFSecSecTentGrpMembObj>() {
			public int compare( ICFSecSecTentGrpMembObj lhs, ICFSecSecTentGrpMembObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					ICFSecSecTentGrpMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecTentGrpMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	/**
	 *	Return a sorted map of a page of the SecTentGrpMemb-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecTentGrpMembObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	@Override
	public List<ICFSecSecTentGrpMembObj> pageAllSecTentGrpMemb(CFLibDbKeyHash256 priorSecTentGrpId,
		CFLibDbKeyHash256 priorSecUserId )
	{
		final String S_ProcName = "pageAllSecTentGrpMemb";
		Map<ICFSecSecTentGrpMembPKey, ICFSecSecTentGrpMembObj> map = new HashMap<ICFSecSecTentGrpMembPKey,ICFSecSecTentGrpMembObj>();
		ICFSecSecTentGrpMemb[] recList = schema.getCFSecBackingStore().getTableSecTentGrpMemb().pageAllRec( null,
			priorSecTentGrpId,
			priorSecUserId );
		ICFSecSecTentGrpMemb rec;
		ICFSecSecTentGrpMembObj obj;
		ICFSecSecTentGrpMembObj realised;
		ArrayList<ICFSecSecTentGrpMembObj> arrayList = new ArrayList<ICFSecSecTentGrpMembObj>( recList.length );
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			realised = (ICFSecSecTentGrpMembObj)obj.realise();
			arrayList.add( realised );
		}
		return( arrayList );
	}

	@Override
	public ICFSecSecTentGrpMembObj readSecTentGrpMembByIdIdx( CFLibDbKeyHash256 SecTentGrpId,
		CFLibDbKeyHash256 SecUserId )
	{
		return( readSecTentGrpMembByIdIdx( SecTentGrpId,
			SecUserId,
			false ) );
	}

	@Override
	public ICFSecSecTentGrpMembObj readSecTentGrpMembByIdIdx( CFLibDbKeyHash256 SecTentGrpId,
		CFLibDbKeyHash256 SecUserId, boolean forceRead )
	{
		ICFSecSecTentGrpMembPKey pkey = schema.getCFSecBackingStore().getFactorySecTentGrpMemb().newPKey();
		pkey.setRequiredSecTentGrpId( SecTentGrpId );
		pkey.setRequiredSecUserId( SecUserId );
		ICFSecSecTentGrpMembObj obj = readSecTentGrpMemb( pkey, forceRead );
		return( obj );
	}

	@Override
	public List<ICFSecSecTentGrpMembObj> readSecTentGrpMembByTentGrpIdx( CFLibDbKeyHash256 SecTentGrpId )
	{
		return( readSecTentGrpMembByTentGrpIdx( SecTentGrpId,
			false ) );
	}

	@Override
	public List<ICFSecSecTentGrpMembObj> readSecTentGrpMembByTentGrpIdx( CFLibDbKeyHash256 SecTentGrpId,
		boolean forceRead )
	{
		final String S_ProcName = "readSecTentGrpMembByTentGrpIdx";
		ICFSecSecTentGrpMembByTentGrpIdxKey key = schema.getCFSecBackingStore().getFactorySecTentGrpMemb().newByTentGrpIdxKey();
		key.setRequiredSecTentGrpId( SecTentGrpId );
		Map<ICFSecSecTentGrpMembPKey, ICFSecSecTentGrpMembObj> dict;
		if( indexByTentGrpIdx == null ) {
			indexByTentGrpIdx = new HashMap< ICFSecSecTentGrpMembByTentGrpIdxKey,
				Map< ICFSecSecTentGrpMembPKey, ICFSecSecTentGrpMembObj > >();
		}
		if( ( ! forceRead ) && indexByTentGrpIdx.containsKey( key ) ) {
			dict = indexByTentGrpIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecSecTentGrpMembPKey, ICFSecSecTentGrpMembObj>();
			ICFSecSecTentGrpMembObj obj;
			ICFSecSecTentGrpMemb[] recList = schema.getCFSecBackingStore().getTableSecTentGrpMemb().readDerivedByTentGrpIdx( null,
				SecTentGrpId );
			ICFSecSecTentGrpMemb rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecTentGrpMembTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecTentGrpMembObj realised = (ICFSecSecTentGrpMembObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByTentGrpIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecTentGrpMembObj arr[] = new ICFSecSecTentGrpMembObj[len];
		Iterator<ICFSecSecTentGrpMembObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFSecSecTentGrpMembObj> arrayList = new ArrayList<ICFSecSecTentGrpMembObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecTentGrpMembObj> cmp = new Comparator<ICFSecSecTentGrpMembObj>() {
			@Override
			public int compare( ICFSecSecTentGrpMembObj lhs, ICFSecSecTentGrpMembObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					ICFSecSecTentGrpMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecTentGrpMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecTentGrpMembObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecTentGrpMembObj> readSecTentGrpMembByUserIdx( CFLibDbKeyHash256 SecUserId )
	{
		return( readSecTentGrpMembByUserIdx( SecUserId,
			false ) );
	}

	@Override
	public List<ICFSecSecTentGrpMembObj> readSecTentGrpMembByUserIdx( CFLibDbKeyHash256 SecUserId,
		boolean forceRead )
	{
		final String S_ProcName = "readSecTentGrpMembByUserIdx";
		ICFSecSecTentGrpMembByUserIdxKey key = schema.getCFSecBackingStore().getFactorySecTentGrpMemb().newByUserIdxKey();
		key.setRequiredSecUserId( SecUserId );
		Map<ICFSecSecTentGrpMembPKey, ICFSecSecTentGrpMembObj> dict;
		if( indexByUserIdx == null ) {
			indexByUserIdx = new HashMap< ICFSecSecTentGrpMembByUserIdxKey,
				Map< ICFSecSecTentGrpMembPKey, ICFSecSecTentGrpMembObj > >();
		}
		if( ( ! forceRead ) && indexByUserIdx.containsKey( key ) ) {
			dict = indexByUserIdx.get( key );
		}
		else {
			dict = new HashMap<ICFSecSecTentGrpMembPKey, ICFSecSecTentGrpMembObj>();
			ICFSecSecTentGrpMembObj obj;
			ICFSecSecTentGrpMemb[] recList = schema.getCFSecBackingStore().getTableSecTentGrpMemb().readDerivedByUserIdx( null,
				SecUserId );
			ICFSecSecTentGrpMemb rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecTentGrpMembTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecTentGrpMembObj realised = (ICFSecSecTentGrpMembObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByUserIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecTentGrpMembObj arr[] = new ICFSecSecTentGrpMembObj[len];
		Iterator<ICFSecSecTentGrpMembObj> valIter = dict.values().iterator();
		int idx = 0;
		while( ( idx < len ) && valIter.hasNext() ) {
			arr[idx++] = valIter.next();
		}
		if( idx < len ) {
			throw new CFLibArgumentUnderflowException( getClass(),
				S_ProcName,
				0,
				"idx",
				idx,
				len );
		}
		else if( valIter.hasNext() ) {
			throw new CFLibArgumentOverflowException( getClass(),
					S_ProcName,
					0,
					"idx",
					idx,
					len );
		}
		ArrayList<ICFSecSecTentGrpMembObj> arrayList = new ArrayList<ICFSecSecTentGrpMembObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecTentGrpMembObj> cmp = new Comparator<ICFSecSecTentGrpMembObj>() {
			@Override
			public int compare( ICFSecSecTentGrpMembObj lhs, ICFSecSecTentGrpMembObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					ICFSecSecTentGrpMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecTentGrpMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecTentGrpMembObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecSecTentGrpMembObj readCachedSecTentGrpMembByIdIdx( CFLibDbKeyHash256 SecTentGrpId,
		CFLibDbKeyHash256 SecUserId )
	{
		ICFSecSecTentGrpMembObj obj = null;
		ICFSecSecTentGrpMembPKey pkey = schema.getCFSecBackingStore().getFactorySecTentGrpMemb().newPKey();
		pkey.setRequiredSecTentGrpId( SecTentGrpId );
		pkey.setRequiredSecUserId( SecUserId );
		pkey.setRequiredSecTentGrpId( SecTentGrpId );
		pkey.setRequiredSecUserId( SecUserId );
		obj = readCachedSecTentGrpMemb( pkey );
		return( obj );
	}

	@Override
	public List<ICFSecSecTentGrpMembObj> readCachedSecTentGrpMembByTentGrpIdx( CFLibDbKeyHash256 SecTentGrpId )
	{
		final String S_ProcName = "readCachedSecTentGrpMembByTentGrpIdx";
		ICFSecSecTentGrpMembByTentGrpIdxKey key = schema.getCFSecBackingStore().getFactorySecTentGrpMemb().newByTentGrpIdxKey();
		key.setRequiredSecTentGrpId( SecTentGrpId );
		ArrayList<ICFSecSecTentGrpMembObj> arrayList = new ArrayList<ICFSecSecTentGrpMembObj>();
		if( indexByTentGrpIdx != null ) {
			Map<ICFSecSecTentGrpMembPKey, ICFSecSecTentGrpMembObj> dict;
			if( indexByTentGrpIdx.containsKey( key ) ) {
				dict = indexByTentGrpIdx.get( key );
				int len = dict.size();
				ICFSecSecTentGrpMembObj arr[] = new ICFSecSecTentGrpMembObj[len];
				Iterator<ICFSecSecTentGrpMembObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFSecSecTentGrpMembObj obj;
			Iterator<ICFSecSecTentGrpMembObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecTentGrpMembObj> cmp = new Comparator<ICFSecSecTentGrpMembObj>() {
			@Override
			public int compare( ICFSecSecTentGrpMembObj lhs, ICFSecSecTentGrpMembObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					ICFSecSecTentGrpMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecTentGrpMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFSecSecTentGrpMembObj> readCachedSecTentGrpMembByUserIdx( CFLibDbKeyHash256 SecUserId )
	{
		final String S_ProcName = "readCachedSecTentGrpMembByUserIdx";
		ICFSecSecTentGrpMembByUserIdxKey key = schema.getCFSecBackingStore().getFactorySecTentGrpMemb().newByUserIdxKey();
		key.setRequiredSecUserId( SecUserId );
		ArrayList<ICFSecSecTentGrpMembObj> arrayList = new ArrayList<ICFSecSecTentGrpMembObj>();
		if( indexByUserIdx != null ) {
			Map<ICFSecSecTentGrpMembPKey, ICFSecSecTentGrpMembObj> dict;
			if( indexByUserIdx.containsKey( key ) ) {
				dict = indexByUserIdx.get( key );
				int len = dict.size();
				ICFSecSecTentGrpMembObj arr[] = new ICFSecSecTentGrpMembObj[len];
				Iterator<ICFSecSecTentGrpMembObj> valIter = dict.values().iterator();
				int idx = 0;
				while( ( idx < len ) && valIter.hasNext() ) {
					arr[idx++] = valIter.next();
				}
				if( idx < len ) {
					throw new CFLibArgumentUnderflowException( getClass(),
						S_ProcName,
						0,
						"idx",
						idx,
						len );
				}
				else if( valIter.hasNext() ) {
					throw new CFLibArgumentOverflowException( getClass(),
							S_ProcName,
							0,
							"idx",
							idx,
							len );
				}
				for( idx = 0; idx < len; idx ++ ) {
					arrayList.add( arr[idx] );
				}
			}
		}
		else {
			ICFSecSecTentGrpMembObj obj;
			Iterator<ICFSecSecTentGrpMembObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecTentGrpMembObj> cmp = new Comparator<ICFSecSecTentGrpMembObj>() {
			@Override
			public int compare( ICFSecSecTentGrpMembObj lhs, ICFSecSecTentGrpMembObj rhs ) {
				if( lhs == null ) {
					if( rhs == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhs == null ) {
					return( 1 );
				}
				else {
					ICFSecSecTentGrpMembPKey lhsPKey = lhs.getPKey();
					ICFSecSecTentGrpMembPKey rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeSecTentGrpMembByIdIdx( CFLibDbKeyHash256 SecTentGrpId,
		CFLibDbKeyHash256 SecUserId )
	{
		ICFSecSecTentGrpMembObj obj = readCachedSecTentGrpMembByIdIdx( SecTentGrpId,
				SecUserId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecTentGrpMembByTentGrpIdx( CFLibDbKeyHash256 SecTentGrpId )
	{
		final String S_ProcName = "deepDisposeSecTentGrpMembByTentGrpIdx";
		ICFSecSecTentGrpMembObj obj;
		List<ICFSecSecTentGrpMembObj> arrayList = readCachedSecTentGrpMembByTentGrpIdx( SecTentGrpId );
		if( arrayList != null )  {
			Iterator<ICFSecSecTentGrpMembObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSecTentGrpMembByUserIdx( CFLibDbKeyHash256 SecUserId )
	{
		final String S_ProcName = "deepDisposeSecTentGrpMembByUserIdx";
		ICFSecSecTentGrpMembObj obj;
		List<ICFSecSecTentGrpMembObj> arrayList = readCachedSecTentGrpMembByUserIdx( SecUserId );
		if( arrayList != null )  {
			Iterator<ICFSecSecTentGrpMembObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	/**
	 *	Read a page of data as a List of SecTentGrpMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate TentGrpIdx key attributes.
	 *
	 *	@param	SecTentGrpId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecTentGrpMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecTentGrpMembObj> pageSecTentGrpMembByTentGrpIdx( CFLibDbKeyHash256 SecTentGrpId,
		CFLibDbKeyHash256 priorSecTentGrpId,
		CFLibDbKeyHash256 priorSecUserId )
	{
		final String S_ProcName = "pageSecTentGrpMembByTentGrpIdx";
		ICFSecSecTentGrpMembByTentGrpIdxKey key = schema.getCFSecBackingStore().getFactorySecTentGrpMemb().newByTentGrpIdxKey();
		key.setRequiredSecTentGrpId( SecTentGrpId );
		List<ICFSecSecTentGrpMembObj> retList = new LinkedList<ICFSecSecTentGrpMembObj>();
		ICFSecSecTentGrpMembObj obj;
		ICFSecSecTentGrpMemb[] recList = schema.getCFSecBackingStore().getTableSecTentGrpMemb().pageRecByTentGrpIdx( null,
				SecTentGrpId,
			priorSecTentGrpId,
			priorSecUserId );
		ICFSecSecTentGrpMemb rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecTentGrpMembTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecTentGrpMembObj realised = (ICFSecSecTentGrpMembObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	/**
	 *	Read a page of data as a List of SecTentGrpMemb-derived instances sorted by their primary keys,
	 *	as identified by the duplicate UserIdx key attributes.
	 *
	 *	@param	SecUserId	The SecTentGrpMemb key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecTentGrpMemb-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecTentGrpMembObj> pageSecTentGrpMembByUserIdx( CFLibDbKeyHash256 SecUserId,
		CFLibDbKeyHash256 priorSecTentGrpId,
		CFLibDbKeyHash256 priorSecUserId )
	{
		final String S_ProcName = "pageSecTentGrpMembByUserIdx";
		ICFSecSecTentGrpMembByUserIdxKey key = schema.getCFSecBackingStore().getFactorySecTentGrpMemb().newByUserIdxKey();
		key.setRequiredSecUserId( SecUserId );
		List<ICFSecSecTentGrpMembObj> retList = new LinkedList<ICFSecSecTentGrpMembObj>();
		ICFSecSecTentGrpMembObj obj;
		ICFSecSecTentGrpMemb[] recList = schema.getCFSecBackingStore().getTableSecTentGrpMemb().pageRecByUserIdx( null,
				SecUserId,
			priorSecTentGrpId,
			priorSecUserId );
		ICFSecSecTentGrpMemb rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecTentGrpMembTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecTentGrpMembObj realised = (ICFSecSecTentGrpMembObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	@Override
	public ICFSecSecTentGrpMembObj updateSecTentGrpMemb( ICFSecSecTentGrpMembObj Obj ) {
		ICFSecSecTentGrpMembObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecTentGrpMemb().updateSecTentGrpMemb( null,
			Obj.getSecTentGrpMembRec() );
		obj = (ICFSecSecTentGrpMembObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSecTentGrpMemb( ICFSecSecTentGrpMembObj Obj ) {
		ICFSecSecTentGrpMembObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecTentGrpMemb().deleteSecTentGrpMemb( null,
			obj.getSecTentGrpMembRec() );
		Obj.forget();
	}

	@Override
	public void deleteSecTentGrpMembByIdIdx( CFLibDbKeyHash256 SecTentGrpId,
		CFLibDbKeyHash256 SecUserId )
	{
		ICFSecSecTentGrpMembObj obj = readSecTentGrpMemb(SecTentGrpId,
				SecUserId);
		if( obj != null ) {
			ICFSecSecTentGrpMembEditObj editObj = (ICFSecSecTentGrpMembEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecSecTentGrpMembEditObj)obj.beginEdit();
				if( editObj != null ) {
					editStarted = true;
				}
				else {
					editStarted = false;
				}
			}
			else {
				editStarted = false;
			}
			if( editObj != null ) {
				editObj.deleteInstance();
				if( editStarted ) {
					editObj.endEdit();
				}
			}
			obj.forget();
		}
		deepDisposeSecTentGrpMembByIdIdx( SecTentGrpId,
				SecUserId );
	}

	@Override
	public void deleteSecTentGrpMembByTentGrpIdx( CFLibDbKeyHash256 SecTentGrpId )
	{
		ICFSecSecTentGrpMembByTentGrpIdxKey key = schema.getCFSecBackingStore().getFactorySecTentGrpMemb().newByTentGrpIdxKey();
		key.setRequiredSecTentGrpId( SecTentGrpId );
		if( indexByTentGrpIdx == null ) {
			indexByTentGrpIdx = new HashMap< ICFSecSecTentGrpMembByTentGrpIdxKey,
				Map< ICFSecSecTentGrpMembPKey, ICFSecSecTentGrpMembObj > >();
		}
		if( indexByTentGrpIdx.containsKey( key ) ) {
			Map<ICFSecSecTentGrpMembPKey, ICFSecSecTentGrpMembObj> dict = indexByTentGrpIdx.get( key );
			schema.getCFSecBackingStore().getTableSecTentGrpMemb().deleteSecTentGrpMembByTentGrpIdx( null,
				SecTentGrpId );
			Iterator<ICFSecSecTentGrpMembObj> iter = dict.values().iterator();
			ICFSecSecTentGrpMembObj obj;
			List<ICFSecSecTentGrpMembObj> toForget = new LinkedList<ICFSecSecTentGrpMembObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByTentGrpIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecTentGrpMemb().deleteSecTentGrpMembByTentGrpIdx( null,
				SecTentGrpId );
		}
		deepDisposeSecTentGrpMembByTentGrpIdx( SecTentGrpId );
	}

	@Override
	public void deleteSecTentGrpMembByUserIdx( CFLibDbKeyHash256 SecUserId )
	{
		ICFSecSecTentGrpMembByUserIdxKey key = schema.getCFSecBackingStore().getFactorySecTentGrpMemb().newByUserIdxKey();
		key.setRequiredSecUserId( SecUserId );
		if( indexByUserIdx == null ) {
			indexByUserIdx = new HashMap< ICFSecSecTentGrpMembByUserIdxKey,
				Map< ICFSecSecTentGrpMembPKey, ICFSecSecTentGrpMembObj > >();
		}
		if( indexByUserIdx.containsKey( key ) ) {
			Map<ICFSecSecTentGrpMembPKey, ICFSecSecTentGrpMembObj> dict = indexByUserIdx.get( key );
			schema.getCFSecBackingStore().getTableSecTentGrpMemb().deleteSecTentGrpMembByUserIdx( null,
				SecUserId );
			Iterator<ICFSecSecTentGrpMembObj> iter = dict.values().iterator();
			ICFSecSecTentGrpMembObj obj;
			List<ICFSecSecTentGrpMembObj> toForget = new LinkedList<ICFSecSecTentGrpMembObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByUserIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecTentGrpMemb().deleteSecTentGrpMembByUserIdx( null,
				SecUserId );
		}
		deepDisposeSecTentGrpMembByUserIdx( SecUserId );
	}
}