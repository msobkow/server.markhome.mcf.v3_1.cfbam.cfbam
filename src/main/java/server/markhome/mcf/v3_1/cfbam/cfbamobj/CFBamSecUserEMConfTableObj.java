// Description: Java 25 Table Object implementation for SecUserEMConf.

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

public class CFBamSecUserEMConfTableObj
	implements ICFBamSecUserEMConfTableObj
{
	protected ICFBamSchemaObj schema;
	private Map<CFLibDbKeyHash256, ICFSecSecUserEMConfObj> members;
	private Map<CFLibDbKeyHash256, ICFSecSecUserEMConfObj> allSecUserEMConf;
	private Map< ICFSecSecUserEMConfByUUuid6IdxKey,
		ICFSecSecUserEMConfObj > indexByUUuid6Idx;
	private Map< ICFSecSecUserEMConfByConfEMAddrIdxKey,
		Map<CFLibDbKeyHash256, ICFSecSecUserEMConfObj > > indexByConfEMAddrIdx;
	private Map< ICFSecSecUserEMConfBySentStampIdxKey,
		Map<CFLibDbKeyHash256, ICFSecSecUserEMConfObj > > indexBySentStampIdx;
	private Map< ICFSecSecUserEMConfByNewAcctIdxKey,
		Map<CFLibDbKeyHash256, ICFSecSecUserEMConfObj > > indexByNewAcctIdx;
	public static String TABLE_NAME = "SecUserEMConf";
	public static String TABLE_DBNAME = "secusremcnf";

	public CFBamSecUserEMConfTableObj() {
		schema = null;
		members = new HashMap<CFLibDbKeyHash256, ICFSecSecUserEMConfObj>();
		allSecUserEMConf = null;
		indexByUUuid6Idx = null;
		indexByConfEMAddrIdx = null;
		indexBySentStampIdx = null;
		indexByNewAcctIdx = null;
	}

	public CFBamSecUserEMConfTableObj( ICFSecSchemaObj argSchema ) {
		schema = (ICFBamSchemaObj)argSchema;
		members = new HashMap<CFLibDbKeyHash256, ICFSecSecUserEMConfObj>();
		allSecUserEMConf = null;
		indexByUUuid6Idx = null;
		indexByConfEMAddrIdx = null;
		indexBySentStampIdx = null;
		indexByNewAcctIdx = null;
	}
	
	/**
	 *	Get class code always returns the runtime class code for the objects, which is not stable until the application is done initializing and registering its objects.
	 *
	 *	@return runtime classcode
	 */ 
	@Override
	public int getClassCode() {
		return CFSecSecUserEMConfTableObj.getRuntimeClassCode();
	}	

	/**
	 *	Get the backing store schema's class code, which is hard-coded into the object hierarchy.
	 *
	 *	@return The hardcoded backing store class code for this object, which is only valid in that schema.
	 */
	public static int getBackingClassCode() {
		return( CFSecSecUserEMConfTableObj.getBackingClassCode() );
	}

	/**
	 *	Get the runtime class code for this table; this value is only stable after the application is fully initialized.
	 *
	 *	@return runtimeClassCode
	 */
	public static int getRuntimeClassCode() {
		return( CFSecSecUserEMConfTableObj.getRuntimeClassCode() );
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
		allSecUserEMConf = null;
		indexByUUuid6Idx = null;
		indexByConfEMAddrIdx = null;
		indexBySentStampIdx = null;
		indexByNewAcctIdx = null;
		List<ICFSecSecUserEMConfObj> toForget = new LinkedList<ICFSecSecUserEMConfObj>();
		ICFSecSecUserEMConfObj cur = null;
		Iterator<ICFSecSecUserEMConfObj> iter = members.values().iterator();
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
	 *	CFBamSecUserEMConfObj.
	 */
	@Override
	public ICFSecSecUserEMConfObj newInstance() {
		ICFSecSecUserEMConfObj inst = new CFBamSecUserEMConfObj( schema );
		return( inst );
	}

	/**
	 *	If your implementation subclasses the objects,
	 *	you'll want to overload the constructByClassCode()
	 *	implementation to return your implementation's
	 *	instances instead of the base implementation.
	 *
	 *	This is the sole factory for instances derived from
	 *	CFBamSecUserEMConfObj.
	 */
	@Override
	public ICFSecSecUserEMConfEditObj newEditInstance( ICFSecSecUserEMConfObj orig ) {
		ICFSecSecUserEMConfEditObj edit = new CFBamSecUserEMConfEditObj( orig );
		return( edit );
	}

	@Override
	public ICFSecSecUserEMConfObj realiseSecUserEMConf( ICFSecSecUserEMConfObj Obj ) {
		ICFSecSecUserEMConfObj obj = Obj;
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFSecSecUserEMConfObj keepObj = null;
		if( members.containsKey( pkey ) && ( null != members.get( pkey ) ) ) {
			ICFSecSecUserEMConfObj existingObj = members.get( pkey );
			keepObj = existingObj;

			/*
			 *	We always rebind the data because if we're being called, some index has
			 *	been updated and is refreshing it's data, which may or may not have changed
			 */

			// Detach object from alternate and duplicate indexes, leave PKey alone

			if( indexByUUuid6Idx != null ) {
				ICFSecSecUserEMConfByUUuid6IdxKey keyUUuid6Idx =
					schema.getCFSecBackingStore().getFactorySecUserEMConf().newByUUuid6IdxKey();
				keyUUuid6Idx.setRequiredEMConfirmationUuid6( keepObj.getRequiredEMConfirmationUuid6() );
				indexByUUuid6Idx.remove( keyUUuid6Idx );
			}

			if( indexByConfEMAddrIdx != null ) {
				ICFSecSecUserEMConfByConfEMAddrIdxKey keyConfEMAddrIdx =
					schema.getCFSecBackingStore().getFactorySecUserEMConf().newByConfEMAddrIdxKey();
				keyConfEMAddrIdx.setRequiredConfirmEMailAddr( keepObj.getRequiredConfirmEMailAddr() );
				Map<CFLibDbKeyHash256, ICFSecSecUserEMConfObj > mapConfEMAddrIdx = indexByConfEMAddrIdx.get( keyConfEMAddrIdx );
				if( mapConfEMAddrIdx != null ) {
					mapConfEMAddrIdx.remove( keepObj.getPKey() );
					if( mapConfEMAddrIdx.size() <= 0 ) {
						indexByConfEMAddrIdx.remove( keyConfEMAddrIdx );
					}
				}
			}

			if( indexBySentStampIdx != null ) {
				ICFSecSecUserEMConfBySentStampIdxKey keySentStampIdx =
					schema.getCFSecBackingStore().getFactorySecUserEMConf().newBySentStampIdxKey();
				keySentStampIdx.setRequiredEMailSentStamp( keepObj.getRequiredEMailSentStamp() );
				Map<CFLibDbKeyHash256, ICFSecSecUserEMConfObj > mapSentStampIdx = indexBySentStampIdx.get( keySentStampIdx );
				if( mapSentStampIdx != null ) {
					mapSentStampIdx.remove( keepObj.getPKey() );
					if( mapSentStampIdx.size() <= 0 ) {
						indexBySentStampIdx.remove( keySentStampIdx );
					}
				}
			}

			if( indexByNewAcctIdx != null ) {
				ICFSecSecUserEMConfByNewAcctIdxKey keyNewAcctIdx =
					schema.getCFSecBackingStore().getFactorySecUserEMConf().newByNewAcctIdxKey();
				keyNewAcctIdx.setRequiredNewAccount( keepObj.getRequiredNewAccount() );
				Map<CFLibDbKeyHash256, ICFSecSecUserEMConfObj > mapNewAcctIdx = indexByNewAcctIdx.get( keyNewAcctIdx );
				if( mapNewAcctIdx != null ) {
					mapNewAcctIdx.remove( keepObj.getPKey() );
					if( mapNewAcctIdx.size() <= 0 ) {
						indexByNewAcctIdx.remove( keyNewAcctIdx );
					}
				}
			}

			keepObj.setRec( Obj.getRec() );
			// Attach new object to alternate and duplicate indexes -- PKey stay stable

			if( indexByUUuid6Idx != null ) {
				ICFSecSecUserEMConfByUUuid6IdxKey keyUUuid6Idx =
					schema.getCFSecBackingStore().getFactorySecUserEMConf().newByUUuid6IdxKey();
				keyUUuid6Idx.setRequiredEMConfirmationUuid6( keepObj.getRequiredEMConfirmationUuid6() );
				indexByUUuid6Idx.put( keyUUuid6Idx, keepObj );
			}

			if( indexByConfEMAddrIdx != null ) {
				ICFSecSecUserEMConfByConfEMAddrIdxKey keyConfEMAddrIdx =
					schema.getCFSecBackingStore().getFactorySecUserEMConf().newByConfEMAddrIdxKey();
				keyConfEMAddrIdx.setRequiredConfirmEMailAddr( keepObj.getRequiredConfirmEMailAddr() );
				Map<CFLibDbKeyHash256, ICFSecSecUserEMConfObj > mapConfEMAddrIdx = indexByConfEMAddrIdx.get( keyConfEMAddrIdx );
				if( mapConfEMAddrIdx != null ) {
					mapConfEMAddrIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexBySentStampIdx != null ) {
				ICFSecSecUserEMConfBySentStampIdxKey keySentStampIdx =
					schema.getCFSecBackingStore().getFactorySecUserEMConf().newBySentStampIdxKey();
				keySentStampIdx.setRequiredEMailSentStamp( keepObj.getRequiredEMailSentStamp() );
				Map<CFLibDbKeyHash256, ICFSecSecUserEMConfObj > mapSentStampIdx = indexBySentStampIdx.get( keySentStampIdx );
				if( mapSentStampIdx != null ) {
					mapSentStampIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNewAcctIdx != null ) {
				ICFSecSecUserEMConfByNewAcctIdxKey keyNewAcctIdx =
					schema.getCFSecBackingStore().getFactorySecUserEMConf().newByNewAcctIdxKey();
				keyNewAcctIdx.setRequiredNewAccount( keepObj.getRequiredNewAccount() );
				Map<CFLibDbKeyHash256, ICFSecSecUserEMConfObj > mapNewAcctIdx = indexByNewAcctIdx.get( keyNewAcctIdx );
				if( mapNewAcctIdx != null ) {
					mapNewAcctIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( allSecUserEMConf != null ) {
				allSecUserEMConf.put( keepObj.getPKey(), keepObj );
			}
		}
		else {
			keepObj = obj;
			keepObj.setIsNew( false );

			// Attach new object to PKey, all, alternate, and duplicate indexes
			members.put( keepObj.getPKey(), keepObj );
			if( allSecUserEMConf != null ) {
				allSecUserEMConf.put( keepObj.getPKey(), keepObj );
			}

			if( indexByUUuid6Idx != null ) {
				ICFSecSecUserEMConfByUUuid6IdxKey keyUUuid6Idx =
					schema.getCFSecBackingStore().getFactorySecUserEMConf().newByUUuid6IdxKey();
				keyUUuid6Idx.setRequiredEMConfirmationUuid6( keepObj.getRequiredEMConfirmationUuid6() );
				indexByUUuid6Idx.put( keyUUuid6Idx, keepObj );
			}

			if( indexByConfEMAddrIdx != null ) {
				ICFSecSecUserEMConfByConfEMAddrIdxKey keyConfEMAddrIdx =
					schema.getCFSecBackingStore().getFactorySecUserEMConf().newByConfEMAddrIdxKey();
				keyConfEMAddrIdx.setRequiredConfirmEMailAddr( keepObj.getRequiredConfirmEMailAddr() );
				Map<CFLibDbKeyHash256, ICFSecSecUserEMConfObj > mapConfEMAddrIdx = indexByConfEMAddrIdx.get( keyConfEMAddrIdx );
				if( mapConfEMAddrIdx != null ) {
					mapConfEMAddrIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexBySentStampIdx != null ) {
				ICFSecSecUserEMConfBySentStampIdxKey keySentStampIdx =
					schema.getCFSecBackingStore().getFactorySecUserEMConf().newBySentStampIdxKey();
				keySentStampIdx.setRequiredEMailSentStamp( keepObj.getRequiredEMailSentStamp() );
				Map<CFLibDbKeyHash256, ICFSecSecUserEMConfObj > mapSentStampIdx = indexBySentStampIdx.get( keySentStampIdx );
				if( mapSentStampIdx != null ) {
					mapSentStampIdx.put( keepObj.getPKey(), keepObj );
				}
			}

			if( indexByNewAcctIdx != null ) {
				ICFSecSecUserEMConfByNewAcctIdxKey keyNewAcctIdx =
					schema.getCFSecBackingStore().getFactorySecUserEMConf().newByNewAcctIdxKey();
				keyNewAcctIdx.setRequiredNewAccount( keepObj.getRequiredNewAccount() );
				Map<CFLibDbKeyHash256, ICFSecSecUserEMConfObj > mapNewAcctIdx = indexByNewAcctIdx.get( keyNewAcctIdx );
				if( mapNewAcctIdx != null ) {
					mapNewAcctIdx.put( keepObj.getPKey(), keepObj );
				}
			}

		}
		return( keepObj );
	}

	@Override
	public ICFSecSecUserEMConfObj createSecUserEMConf( ICFSecSecUserEMConfObj Obj ) {
		ICFSecSecUserEMConfObj obj = Obj;
		ICFSecSecUserEMConf rec = obj.getSecUserEMConfRec();
		schema.getCFSecBackingStore().getTableSecUserEMConf().createSecUserEMConf(
			null,
			rec );
		obj.copyRecToPKey();
		obj = obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public ICFSecSecUserEMConfObj readSecUserEMConf( CFLibDbKeyHash256 pkey ) {
		return( readSecUserEMConf( pkey, false ) );
	}

	@Override
	public ICFSecSecUserEMConfObj readSecUserEMConf( CFLibDbKeyHash256 pkey, boolean forceRead ) {
		ICFSecSecUserEMConfObj obj = null;
		if( ( ! forceRead ) && members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		else {
			ICFSecSecUserEMConf readRec = schema.getCFSecBackingStore().getTableSecUserEMConf().readDerivedByIdIdx( null,
						pkey );
			if( readRec != null ) {
				obj = schema.getSecUserEMConfTableObj().newInstance();
				obj.setPKey( readRec.getPKey() );
				obj.setRec( readRec );
				obj = (ICFSecSecUserEMConfObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public ICFSecSecUserEMConfObj readCachedSecUserEMConf( CFLibDbKeyHash256 pkey ) {
		ICFSecSecUserEMConfObj obj = null;
		if( members.containsKey( pkey ) ) {
			obj = members.get( pkey );
		}
		return( obj );
	}

	@Override
	public void reallyDeepDisposeSecUserEMConf( ICFSecSecUserEMConfObj obj )
	{
		final String S_ProcName = "CFBamSecUserEMConfTableObj.reallyDeepDisposeSecUserEMConf() ";
		String classCode;
		if( obj == null ) {
			return;
		}
		CFLibDbKeyHash256 pkey = obj.getPKey();
		ICFSecSecUserEMConfObj existing = readCachedSecUserEMConf( pkey );
		if( existing == null ) {
			return;
		}
		members.remove( pkey );
		ICFSecSecUserEMConfByUUuid6IdxKey keyUUuid6Idx = schema.getCFSecBackingStore().getFactorySecUserEMConf().newByUUuid6IdxKey();
		keyUUuid6Idx.setRequiredEMConfirmationUuid6( existing.getRequiredEMConfirmationUuid6() );

		ICFSecSecUserEMConfByConfEMAddrIdxKey keyConfEMAddrIdx = schema.getCFSecBackingStore().getFactorySecUserEMConf().newByConfEMAddrIdxKey();
		keyConfEMAddrIdx.setRequiredConfirmEMailAddr( existing.getRequiredConfirmEMailAddr() );

		ICFSecSecUserEMConfBySentStampIdxKey keySentStampIdx = schema.getCFSecBackingStore().getFactorySecUserEMConf().newBySentStampIdxKey();
		keySentStampIdx.setRequiredEMailSentStamp( existing.getRequiredEMailSentStamp() );

		ICFSecSecUserEMConfByNewAcctIdxKey keyNewAcctIdx = schema.getCFSecBackingStore().getFactorySecUserEMConf().newByNewAcctIdxKey();
		keyNewAcctIdx.setRequiredNewAccount( existing.getRequiredNewAccount() );



		if( indexByUUuid6Idx != null ) {
			indexByUUuid6Idx.remove( keyUUuid6Idx );
		}

		if( indexByConfEMAddrIdx != null ) {
			if( indexByConfEMAddrIdx.containsKey( keyConfEMAddrIdx ) ) {
				indexByConfEMAddrIdx.get( keyConfEMAddrIdx ).remove( pkey );
				if( indexByConfEMAddrIdx.get( keyConfEMAddrIdx ).size() <= 0 ) {
					indexByConfEMAddrIdx.remove( keyConfEMAddrIdx );
				}
			}
		}

		if( indexBySentStampIdx != null ) {
			if( indexBySentStampIdx.containsKey( keySentStampIdx ) ) {
				indexBySentStampIdx.get( keySentStampIdx ).remove( pkey );
				if( indexBySentStampIdx.get( keySentStampIdx ).size() <= 0 ) {
					indexBySentStampIdx.remove( keySentStampIdx );
				}
			}
		}

		if( indexByNewAcctIdx != null ) {
			if( indexByNewAcctIdx.containsKey( keyNewAcctIdx ) ) {
				indexByNewAcctIdx.get( keyNewAcctIdx ).remove( pkey );
				if( indexByNewAcctIdx.get( keyNewAcctIdx ).size() <= 0 ) {
					indexByNewAcctIdx.remove( keyNewAcctIdx );
				}
			}
		}


	}
	@Override
	public void deepDisposeSecUserEMConf( CFLibDbKeyHash256 pkey ) {
		ICFSecSecUserEMConfObj obj = readCachedSecUserEMConf( pkey );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public ICFSecSecUserEMConfObj lockSecUserEMConf( CFLibDbKeyHash256 pkey ) {
		ICFSecSecUserEMConfObj locked = null;
		ICFSecSecUserEMConf lockRec = schema.getCFSecBackingStore().getTableSecUserEMConf().lockDerived( null, pkey );
		if( lockRec != null ) {
				locked = schema.getSecUserEMConfTableObj().newInstance();
			locked.setRec( lockRec );
			locked.setPKey( lockRec.getPKey() );
			locked = (ICFSecSecUserEMConfObj)locked.realise();
		}
		else {
			throw new CFLibCollisionDetectedException( getClass(), "lockSecUserEMConf", pkey );
		}
		return( locked );
	}

	@Override
	public List<ICFSecSecUserEMConfObj> readAllSecUserEMConf() {
		return( readAllSecUserEMConf( false ) );
	}

	@Override
	public List<ICFSecSecUserEMConfObj> readAllSecUserEMConf( boolean forceRead ) {
		final String S_ProcName = "readAllSecUserEMConf";
		if( ( allSecUserEMConf == null ) || forceRead ) {
			Map<CFLibDbKeyHash256, ICFSecSecUserEMConfObj> map = new HashMap<CFLibDbKeyHash256,ICFSecSecUserEMConfObj>();
			allSecUserEMConf = map;
			ICFSecSecUserEMConf[] recList = schema.getCFSecBackingStore().getTableSecUserEMConf().readAllDerived( null );
			ICFSecSecUserEMConf rec;
			ICFSecSecUserEMConfObj obj;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecUserEMConfObj realised = (ICFSecSecUserEMConfObj)obj.realise();
			}
		}
		int len = allSecUserEMConf.size();
		ICFSecSecUserEMConfObj arr[] = new ICFSecSecUserEMConfObj[len];
		Iterator<ICFSecSecUserEMConfObj> valIter = allSecUserEMConf.values().iterator();
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
		ArrayList<ICFSecSecUserEMConfObj> arrayList = new ArrayList<ICFSecSecUserEMConfObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecUserEMConfObj> cmp = new Comparator<ICFSecSecUserEMConfObj>() {
			@Override
			public int compare( ICFSecSecUserEMConfObj lhs, ICFSecSecUserEMConfObj rhs ) {
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
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecUserEMConfObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecUserEMConfObj> readCachedAllSecUserEMConf() {
		final String S_ProcName = "readCachedAllSecUserEMConf";
		ArrayList<ICFSecSecUserEMConfObj> arrayList = new ArrayList<ICFSecSecUserEMConfObj>();
		if( allSecUserEMConf != null ) {
			int len = allSecUserEMConf.size();
			ICFSecSecUserEMConfObj arr[] = new ICFSecSecUserEMConfObj[len];
			Iterator<ICFSecSecUserEMConfObj> valIter = allSecUserEMConf.values().iterator();
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
		Comparator<ICFSecSecUserEMConfObj> cmp = new Comparator<ICFSecSecUserEMConfObj>() {
			public int compare( ICFSecSecUserEMConfObj lhs, ICFSecSecUserEMConfObj rhs ) {
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
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	/**
	 *	Return a sorted map of a page of the SecUserEMConf-derived instances in the database.
	 *
	 *	@return	List of ICFSecSecUserEMConfObj instance, sorted by their primary keys, which
	 *		may include an empty set.
	 */
	@Override
	public List<ICFSecSecUserEMConfObj> pageAllSecUserEMConf(CFLibDbKeyHash256 priorSecUserId )
	{
		final String S_ProcName = "pageAllSecUserEMConf";
		Map<CFLibDbKeyHash256, ICFSecSecUserEMConfObj> map = new HashMap<CFLibDbKeyHash256,ICFSecSecUserEMConfObj>();
		ICFSecSecUserEMConf[] recList = schema.getCFSecBackingStore().getTableSecUserEMConf().pageAllRec( null,
			priorSecUserId );
		ICFSecSecUserEMConf rec;
		ICFSecSecUserEMConfObj obj;
		ICFSecSecUserEMConfObj realised;
		ArrayList<ICFSecSecUserEMConfObj> arrayList = new ArrayList<ICFSecSecUserEMConfObj>( recList.length );
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			realised = (ICFSecSecUserEMConfObj)obj.realise();
			arrayList.add( realised );
		}
		return( arrayList );
	}

	@Override
	public ICFSecSecUserEMConfObj readSecUserEMConfByIdIdx( CFLibDbKeyHash256 SecUserId )
	{
		return( readSecUserEMConfByIdIdx( SecUserId,
			false ) );
	}

	@Override
	public ICFSecSecUserEMConfObj readSecUserEMConfByIdIdx( CFLibDbKeyHash256 SecUserId, boolean forceRead )
	{
		ICFSecSecUserEMConfObj obj = readSecUserEMConf( SecUserId, forceRead );
		return( obj );
	}

	@Override
	public ICFSecSecUserEMConfObj readSecUserEMConfByUUuid6Idx( CFLibUuid6 EMConfirmationUuid6 )
	{
		return( readSecUserEMConfByUUuid6Idx( EMConfirmationUuid6,
			false ) );
	}

	@Override
	public ICFSecSecUserEMConfObj readSecUserEMConfByUUuid6Idx( CFLibUuid6 EMConfirmationUuid6, boolean forceRead )
	{
		if( indexByUUuid6Idx == null ) {
			indexByUUuid6Idx = new HashMap< ICFSecSecUserEMConfByUUuid6IdxKey,
				ICFSecSecUserEMConfObj >();
		}
		ICFSecSecUserEMConfByUUuid6IdxKey key = schema.getCFSecBackingStore().getFactorySecUserEMConf().newByUUuid6IdxKey();
		key.setRequiredEMConfirmationUuid6( EMConfirmationUuid6 );
		ICFSecSecUserEMConfObj obj = null;
		if( ( ! forceRead ) && indexByUUuid6Idx.containsKey( key ) ) {
			obj = indexByUUuid6Idx.get( key );
		}
		else {
			ICFSecSecUserEMConf rec = schema.getCFSecBackingStore().getTableSecUserEMConf().readDerivedByUUuid6Idx( null,
				EMConfirmationUuid6 );
			if( rec != null ) {
				obj = schema.getSecUserEMConfTableObj().newInstance();
				obj.setRec( rec );
				obj.setPKey( rec.getPKey() );
				obj = (ICFSecSecUserEMConfObj)obj.realise();
			}
		}
		return( obj );
	}

	@Override
	public List<ICFSecSecUserEMConfObj> readSecUserEMConfByConfEMAddrIdx( String ConfirmEMailAddr )
	{
		return( readSecUserEMConfByConfEMAddrIdx( ConfirmEMailAddr,
			false ) );
	}

	@Override
	public List<ICFSecSecUserEMConfObj> readSecUserEMConfByConfEMAddrIdx( String ConfirmEMailAddr,
		boolean forceRead )
	{
		final String S_ProcName = "readSecUserEMConfByConfEMAddrIdx";
		ICFSecSecUserEMConfByConfEMAddrIdxKey key = schema.getCFSecBackingStore().getFactorySecUserEMConf().newByConfEMAddrIdxKey();
		key.setRequiredConfirmEMailAddr( ConfirmEMailAddr );
		Map<CFLibDbKeyHash256, ICFSecSecUserEMConfObj> dict;
		if( indexByConfEMAddrIdx == null ) {
			indexByConfEMAddrIdx = new HashMap< ICFSecSecUserEMConfByConfEMAddrIdxKey,
				Map< CFLibDbKeyHash256, ICFSecSecUserEMConfObj > >();
		}
		if( ( ! forceRead ) && indexByConfEMAddrIdx.containsKey( key ) ) {
			dict = indexByConfEMAddrIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFSecSecUserEMConfObj>();
			ICFSecSecUserEMConfObj obj;
			ICFSecSecUserEMConf[] recList = schema.getCFSecBackingStore().getTableSecUserEMConf().readDerivedByConfEMAddrIdx( null,
				ConfirmEMailAddr );
			ICFSecSecUserEMConf rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecUserEMConfTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecUserEMConfObj realised = (ICFSecSecUserEMConfObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByConfEMAddrIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecUserEMConfObj arr[] = new ICFSecSecUserEMConfObj[len];
		Iterator<ICFSecSecUserEMConfObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecUserEMConfObj> arrayList = new ArrayList<ICFSecSecUserEMConfObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecUserEMConfObj> cmp = new Comparator<ICFSecSecUserEMConfObj>() {
			@Override
			public int compare( ICFSecSecUserEMConfObj lhs, ICFSecSecUserEMConfObj rhs ) {
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
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecUserEMConfObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecUserEMConfObj> readSecUserEMConfBySentStampIdx( LocalDateTime EMailSentStamp )
	{
		return( readSecUserEMConfBySentStampIdx( EMailSentStamp,
			false ) );
	}

	@Override
	public List<ICFSecSecUserEMConfObj> readSecUserEMConfBySentStampIdx( LocalDateTime EMailSentStamp,
		boolean forceRead )
	{
		final String S_ProcName = "readSecUserEMConfBySentStampIdx";
		ICFSecSecUserEMConfBySentStampIdxKey key = schema.getCFSecBackingStore().getFactorySecUserEMConf().newBySentStampIdxKey();
		key.setRequiredEMailSentStamp( EMailSentStamp );
		Map<CFLibDbKeyHash256, ICFSecSecUserEMConfObj> dict;
		if( indexBySentStampIdx == null ) {
			indexBySentStampIdx = new HashMap< ICFSecSecUserEMConfBySentStampIdxKey,
				Map< CFLibDbKeyHash256, ICFSecSecUserEMConfObj > >();
		}
		if( ( ! forceRead ) && indexBySentStampIdx.containsKey( key ) ) {
			dict = indexBySentStampIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFSecSecUserEMConfObj>();
			ICFSecSecUserEMConfObj obj;
			ICFSecSecUserEMConf[] recList = schema.getCFSecBackingStore().getTableSecUserEMConf().readDerivedBySentStampIdx( null,
				EMailSentStamp );
			ICFSecSecUserEMConf rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecUserEMConfTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecUserEMConfObj realised = (ICFSecSecUserEMConfObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexBySentStampIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecUserEMConfObj arr[] = new ICFSecSecUserEMConfObj[len];
		Iterator<ICFSecSecUserEMConfObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecUserEMConfObj> arrayList = new ArrayList<ICFSecSecUserEMConfObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecUserEMConfObj> cmp = new Comparator<ICFSecSecUserEMConfObj>() {
			@Override
			public int compare( ICFSecSecUserEMConfObj lhs, ICFSecSecUserEMConfObj rhs ) {
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
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecUserEMConfObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public List<ICFSecSecUserEMConfObj> readSecUserEMConfByNewAcctIdx( boolean NewAccount )
	{
		return( readSecUserEMConfByNewAcctIdx( NewAccount,
			false ) );
	}

	@Override
	public List<ICFSecSecUserEMConfObj> readSecUserEMConfByNewAcctIdx( boolean NewAccount,
		boolean forceRead )
	{
		final String S_ProcName = "readSecUserEMConfByNewAcctIdx";
		ICFSecSecUserEMConfByNewAcctIdxKey key = schema.getCFSecBackingStore().getFactorySecUserEMConf().newByNewAcctIdxKey();
		key.setRequiredNewAccount( NewAccount );
		Map<CFLibDbKeyHash256, ICFSecSecUserEMConfObj> dict;
		if( indexByNewAcctIdx == null ) {
			indexByNewAcctIdx = new HashMap< ICFSecSecUserEMConfByNewAcctIdxKey,
				Map< CFLibDbKeyHash256, ICFSecSecUserEMConfObj > >();
		}
		if( ( ! forceRead ) && indexByNewAcctIdx.containsKey( key ) ) {
			dict = indexByNewAcctIdx.get( key );
		}
		else {
			dict = new HashMap<CFLibDbKeyHash256, ICFSecSecUserEMConfObj>();
			ICFSecSecUserEMConfObj obj;
			ICFSecSecUserEMConf[] recList = schema.getCFSecBackingStore().getTableSecUserEMConf().readDerivedByNewAcctIdx( null,
				NewAccount );
			ICFSecSecUserEMConf rec;
			for( int idx = 0; idx < recList.length; idx ++ ) {
				rec = recList[ idx ];
				obj = schema.getSecUserEMConfTableObj().newInstance();
				obj.setPKey( rec.getPKey() );
				obj.setRec( rec );
				ICFSecSecUserEMConfObj realised = (ICFSecSecUserEMConfObj)obj.realise();
				dict.put( realised.getPKey(), realised );
			}
			indexByNewAcctIdx.put( key, dict );
		}
		int len = dict.size();
		ICFSecSecUserEMConfObj arr[] = new ICFSecSecUserEMConfObj[len];
		Iterator<ICFSecSecUserEMConfObj> valIter = dict.values().iterator();
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
		ArrayList<ICFSecSecUserEMConfObj> arrayList = new ArrayList<ICFSecSecUserEMConfObj>(len);
		for( idx = 0; idx < len; idx ++ ) {
			arrayList.add( arr[idx] );
		}

		Comparator<ICFSecSecUserEMConfObj> cmp = new Comparator<ICFSecSecUserEMConfObj>() {
			@Override
			public int compare( ICFSecSecUserEMConfObj lhs, ICFSecSecUserEMConfObj rhs ) {
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
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		List<ICFSecSecUserEMConfObj> sortedList = arrayList;
		return( sortedList );
	}

	@Override
	public ICFSecSecUserEMConfObj readCachedSecUserEMConfByIdIdx( CFLibDbKeyHash256 SecUserId )
	{
		ICFSecSecUserEMConfObj obj = null;
		obj = readCachedSecUserEMConf( SecUserId );
		return( obj );
	}

	@Override
	public ICFSecSecUserEMConfObj readCachedSecUserEMConfByUUuid6Idx( CFLibUuid6 EMConfirmationUuid6 )
	{
		ICFSecSecUserEMConfObj obj = null;
		ICFSecSecUserEMConfByUUuid6IdxKey key = schema.getCFSecBackingStore().getFactorySecUserEMConf().newByUUuid6IdxKey();
		key.setRequiredEMConfirmationUuid6( EMConfirmationUuid6 );
		if( indexByUUuid6Idx != null ) {
			if( indexByUUuid6Idx.containsKey( key ) ) {
				obj = indexByUUuid6Idx.get( key );
			}
			else {
				Iterator<ICFSecSecUserEMConfObj> valIter = members.values().iterator();
				while( ( obj == null ) && valIter.hasNext() ) {
					obj = valIter.next();
					if( obj != null ) {
						if( obj.getRec().compareTo( key ) != 0 ) {
							obj = null;
						}
					}
				}
			}
		}
		else {
			Iterator<ICFSecSecUserEMConfObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) != 0 ) {
						obj = null;
					}
				}
			}
		}
		return( obj );
	}

	@Override
	public List<ICFSecSecUserEMConfObj> readCachedSecUserEMConfByConfEMAddrIdx( String ConfirmEMailAddr )
	{
		final String S_ProcName = "readCachedSecUserEMConfByConfEMAddrIdx";
		ICFSecSecUserEMConfByConfEMAddrIdxKey key = schema.getCFSecBackingStore().getFactorySecUserEMConf().newByConfEMAddrIdxKey();
		key.setRequiredConfirmEMailAddr( ConfirmEMailAddr );
		ArrayList<ICFSecSecUserEMConfObj> arrayList = new ArrayList<ICFSecSecUserEMConfObj>();
		if( indexByConfEMAddrIdx != null ) {
			Map<CFLibDbKeyHash256, ICFSecSecUserEMConfObj> dict;
			if( indexByConfEMAddrIdx.containsKey( key ) ) {
				dict = indexByConfEMAddrIdx.get( key );
				int len = dict.size();
				ICFSecSecUserEMConfObj arr[] = new ICFSecSecUserEMConfObj[len];
				Iterator<ICFSecSecUserEMConfObj> valIter = dict.values().iterator();
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
			ICFSecSecUserEMConfObj obj;
			Iterator<ICFSecSecUserEMConfObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecUserEMConfObj> cmp = new Comparator<ICFSecSecUserEMConfObj>() {
			@Override
			public int compare( ICFSecSecUserEMConfObj lhs, ICFSecSecUserEMConfObj rhs ) {
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
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFSecSecUserEMConfObj> readCachedSecUserEMConfBySentStampIdx( LocalDateTime EMailSentStamp )
	{
		final String S_ProcName = "readCachedSecUserEMConfBySentStampIdx";
		ICFSecSecUserEMConfBySentStampIdxKey key = schema.getCFSecBackingStore().getFactorySecUserEMConf().newBySentStampIdxKey();
		key.setRequiredEMailSentStamp( EMailSentStamp );
		ArrayList<ICFSecSecUserEMConfObj> arrayList = new ArrayList<ICFSecSecUserEMConfObj>();
		if( indexBySentStampIdx != null ) {
			Map<CFLibDbKeyHash256, ICFSecSecUserEMConfObj> dict;
			if( indexBySentStampIdx.containsKey( key ) ) {
				dict = indexBySentStampIdx.get( key );
				int len = dict.size();
				ICFSecSecUserEMConfObj arr[] = new ICFSecSecUserEMConfObj[len];
				Iterator<ICFSecSecUserEMConfObj> valIter = dict.values().iterator();
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
			ICFSecSecUserEMConfObj obj;
			Iterator<ICFSecSecUserEMConfObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecUserEMConfObj> cmp = new Comparator<ICFSecSecUserEMConfObj>() {
			@Override
			public int compare( ICFSecSecUserEMConfObj lhs, ICFSecSecUserEMConfObj rhs ) {
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
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public List<ICFSecSecUserEMConfObj> readCachedSecUserEMConfByNewAcctIdx( boolean NewAccount )
	{
		final String S_ProcName = "readCachedSecUserEMConfByNewAcctIdx";
		ICFSecSecUserEMConfByNewAcctIdxKey key = schema.getCFSecBackingStore().getFactorySecUserEMConf().newByNewAcctIdxKey();
		key.setRequiredNewAccount( NewAccount );
		ArrayList<ICFSecSecUserEMConfObj> arrayList = new ArrayList<ICFSecSecUserEMConfObj>();
		if( indexByNewAcctIdx != null ) {
			Map<CFLibDbKeyHash256, ICFSecSecUserEMConfObj> dict;
			if( indexByNewAcctIdx.containsKey( key ) ) {
				dict = indexByNewAcctIdx.get( key );
				int len = dict.size();
				ICFSecSecUserEMConfObj arr[] = new ICFSecSecUserEMConfObj[len];
				Iterator<ICFSecSecUserEMConfObj> valIter = dict.values().iterator();
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
			ICFSecSecUserEMConfObj obj;
			Iterator<ICFSecSecUserEMConfObj> valIter = members.values().iterator();
			while( valIter.hasNext() ) {
				obj = valIter.next();
				if( obj != null ) {
					if( obj.getRec().compareTo( key ) == 0 ) {
						arrayList.add( obj );
					}
				}
			}
		}
		Comparator<ICFSecSecUserEMConfObj> cmp = new Comparator<ICFSecSecUserEMConfObj>() {
			@Override
			public int compare( ICFSecSecUserEMConfObj lhs, ICFSecSecUserEMConfObj rhs ) {
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
					CFLibDbKeyHash256 lhsPKey = lhs.getPKey();
					CFLibDbKeyHash256 rhsPKey = rhs.getPKey();
					int ret = lhsPKey.compareTo( rhsPKey );
					return( ret );
				}
			}
		};
		Collections.sort( arrayList, cmp );
		return( arrayList );
	}

	@Override
	public void deepDisposeSecUserEMConfByIdIdx( CFLibDbKeyHash256 SecUserId )
	{
		ICFSecSecUserEMConfObj obj = readCachedSecUserEMConfByIdIdx( SecUserId );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecUserEMConfByUUuid6Idx( CFLibUuid6 EMConfirmationUuid6 )
	{
		ICFSecSecUserEMConfObj obj = readCachedSecUserEMConfByUUuid6Idx( EMConfirmationUuid6 );
		if( obj != null ) {
			obj.forget();
		}
	}

	@Override
	public void deepDisposeSecUserEMConfByConfEMAddrIdx( String ConfirmEMailAddr )
	{
		final String S_ProcName = "deepDisposeSecUserEMConfByConfEMAddrIdx";
		ICFSecSecUserEMConfObj obj;
		List<ICFSecSecUserEMConfObj> arrayList = readCachedSecUserEMConfByConfEMAddrIdx( ConfirmEMailAddr );
		if( arrayList != null )  {
			Iterator<ICFSecSecUserEMConfObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSecUserEMConfBySentStampIdx( LocalDateTime EMailSentStamp )
	{
		final String S_ProcName = "deepDisposeSecUserEMConfBySentStampIdx";
		ICFSecSecUserEMConfObj obj;
		List<ICFSecSecUserEMConfObj> arrayList = readCachedSecUserEMConfBySentStampIdx( EMailSentStamp );
		if( arrayList != null )  {
			Iterator<ICFSecSecUserEMConfObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	@Override
	public void deepDisposeSecUserEMConfByNewAcctIdx( boolean NewAccount )
	{
		final String S_ProcName = "deepDisposeSecUserEMConfByNewAcctIdx";
		ICFSecSecUserEMConfObj obj;
		List<ICFSecSecUserEMConfObj> arrayList = readCachedSecUserEMConfByNewAcctIdx( NewAccount );
		if( arrayList != null )  {
			Iterator<ICFSecSecUserEMConfObj> arrayIter = arrayList.iterator();
			while( arrayIter.hasNext() ) {
				obj = arrayIter.next();
				if( obj != null ) {
					obj.forget();
				}
			}
		}
	}

	/**
	 *	Read a page of data as a List of SecUserEMConf-derived instances sorted by their primary keys,
	 *	as identified by the duplicate ConfEMAddrIdx key attributes.
	 *
	 *	@param	ConfirmEMailAddr	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecUserEMConf-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecUserEMConfObj> pageSecUserEMConfByConfEMAddrIdx( String ConfirmEMailAddr,
		CFLibDbKeyHash256 priorSecUserId )
	{
		final String S_ProcName = "pageSecUserEMConfByConfEMAddrIdx";
		ICFSecSecUserEMConfByConfEMAddrIdxKey key = schema.getCFSecBackingStore().getFactorySecUserEMConf().newByConfEMAddrIdxKey();
		key.setRequiredConfirmEMailAddr( ConfirmEMailAddr );
		List<ICFSecSecUserEMConfObj> retList = new LinkedList<ICFSecSecUserEMConfObj>();
		ICFSecSecUserEMConfObj obj;
		ICFSecSecUserEMConf[] recList = schema.getCFSecBackingStore().getTableSecUserEMConf().pageRecByConfEMAddrIdx( null,
				ConfirmEMailAddr,
			priorSecUserId );
		ICFSecSecUserEMConf rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecUserEMConfTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecUserEMConfObj realised = (ICFSecSecUserEMConfObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	/**
	 *	Read a page of data as a List of SecUserEMConf-derived instances sorted by their primary keys,
	 *	as identified by the duplicate SentStampIdx key attributes.
	 *
	 *	@param	EMailSentStamp	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecUserEMConf-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecUserEMConfObj> pageSecUserEMConfBySentStampIdx( LocalDateTime EMailSentStamp,
		CFLibDbKeyHash256 priorSecUserId )
	{
		final String S_ProcName = "pageSecUserEMConfBySentStampIdx";
		ICFSecSecUserEMConfBySentStampIdxKey key = schema.getCFSecBackingStore().getFactorySecUserEMConf().newBySentStampIdxKey();
		key.setRequiredEMailSentStamp( EMailSentStamp );
		List<ICFSecSecUserEMConfObj> retList = new LinkedList<ICFSecSecUserEMConfObj>();
		ICFSecSecUserEMConfObj obj;
		ICFSecSecUserEMConf[] recList = schema.getCFSecBackingStore().getTableSecUserEMConf().pageRecBySentStampIdx( null,
				EMailSentStamp,
			priorSecUserId );
		ICFSecSecUserEMConf rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecUserEMConfTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecUserEMConfObj realised = (ICFSecSecUserEMConfObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	/**
	 *	Read a page of data as a List of SecUserEMConf-derived instances sorted by their primary keys,
	 *	as identified by the duplicate NewAcctIdx key attributes.
	 *
	 *	@param	NewAccount	The SecUserEMConf key attribute of the instance generating the id.
	 *
	 *	@return	A List of SecUserEMConf-derived instances sorted by their primary keys,
	 *		as identified by the key attributes, which may be an empty set.
	 */
	@Override
	public List<ICFSecSecUserEMConfObj> pageSecUserEMConfByNewAcctIdx( boolean NewAccount,
		CFLibDbKeyHash256 priorSecUserId )
	{
		final String S_ProcName = "pageSecUserEMConfByNewAcctIdx";
		ICFSecSecUserEMConfByNewAcctIdxKey key = schema.getCFSecBackingStore().getFactorySecUserEMConf().newByNewAcctIdxKey();
		key.setRequiredNewAccount( NewAccount );
		List<ICFSecSecUserEMConfObj> retList = new LinkedList<ICFSecSecUserEMConfObj>();
		ICFSecSecUserEMConfObj obj;
		ICFSecSecUserEMConf[] recList = schema.getCFSecBackingStore().getTableSecUserEMConf().pageRecByNewAcctIdx( null,
				NewAccount,
			priorSecUserId );
		ICFSecSecUserEMConf rec;
		for( int idx = 0; idx < recList.length; idx ++ ) {
			rec = recList[ idx ];
				obj = schema.getSecUserEMConfTableObj().newInstance();
			obj.setPKey( rec.getPKey() );
			obj.setRec( rec );
			ICFSecSecUserEMConfObj realised = (ICFSecSecUserEMConfObj)obj.realise();
			retList.add( realised );
		}
		return( retList );
	}

	@Override
	public ICFSecSecUserEMConfObj updateSecUserEMConf( ICFSecSecUserEMConfObj Obj ) {
		ICFSecSecUserEMConfObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecUserEMConf().updateSecUserEMConf( null,
			Obj.getSecUserEMConfRec() );
		obj = (ICFSecSecUserEMConfObj)Obj.realise();
		obj.endEdit();
		return( obj );
	}

	@Override
	public void deleteSecUserEMConf( ICFSecSecUserEMConfObj Obj ) {
		ICFSecSecUserEMConfObj obj = Obj;
		schema.getCFSecBackingStore().getTableSecUserEMConf().deleteSecUserEMConf( null,
			obj.getSecUserEMConfRec() );
		Obj.forget();
	}

	@Override
	public void deleteSecUserEMConfByIdIdx( CFLibDbKeyHash256 SecUserId )
	{
		ICFSecSecUserEMConfObj obj = readSecUserEMConf(SecUserId);
		if( obj != null ) {
			ICFSecSecUserEMConfEditObj editObj = (ICFSecSecUserEMConfEditObj)obj.getEdit();
			boolean editStarted;
			if( editObj == null ) {
				editObj = (ICFSecSecUserEMConfEditObj)obj.beginEdit();
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
		deepDisposeSecUserEMConfByIdIdx( SecUserId );
	}

	@Override
	public void deleteSecUserEMConfByUUuid6Idx( CFLibUuid6 EMConfirmationUuid6 )
	{
		if( indexByUUuid6Idx == null ) {
			indexByUUuid6Idx = new HashMap< ICFSecSecUserEMConfByUUuid6IdxKey,
				ICFSecSecUserEMConfObj >();
		}
		ICFSecSecUserEMConfByUUuid6IdxKey key = schema.getCFSecBackingStore().getFactorySecUserEMConf().newByUUuid6IdxKey();
		key.setRequiredEMConfirmationUuid6( EMConfirmationUuid6 );
		ICFSecSecUserEMConfObj obj = null;
		if( indexByUUuid6Idx.containsKey( key ) ) {
			obj = indexByUUuid6Idx.get( key );
			schema.getCFSecBackingStore().getTableSecUserEMConf().deleteSecUserEMConfByUUuid6Idx( null,
				EMConfirmationUuid6 );
			obj.forget();
		}
		else {
			schema.getCFSecBackingStore().getTableSecUserEMConf().deleteSecUserEMConfByUUuid6Idx( null,
				EMConfirmationUuid6 );
		}
		deepDisposeSecUserEMConfByUUuid6Idx( EMConfirmationUuid6 );
	}

	@Override
	public void deleteSecUserEMConfByConfEMAddrIdx( String ConfirmEMailAddr )
	{
		ICFSecSecUserEMConfByConfEMAddrIdxKey key = schema.getCFSecBackingStore().getFactorySecUserEMConf().newByConfEMAddrIdxKey();
		key.setRequiredConfirmEMailAddr( ConfirmEMailAddr );
		if( indexByConfEMAddrIdx == null ) {
			indexByConfEMAddrIdx = new HashMap< ICFSecSecUserEMConfByConfEMAddrIdxKey,
				Map< CFLibDbKeyHash256, ICFSecSecUserEMConfObj > >();
		}
		if( indexByConfEMAddrIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFSecSecUserEMConfObj> dict = indexByConfEMAddrIdx.get( key );
			schema.getCFSecBackingStore().getTableSecUserEMConf().deleteSecUserEMConfByConfEMAddrIdx( null,
				ConfirmEMailAddr );
			Iterator<ICFSecSecUserEMConfObj> iter = dict.values().iterator();
			ICFSecSecUserEMConfObj obj;
			List<ICFSecSecUserEMConfObj> toForget = new LinkedList<ICFSecSecUserEMConfObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByConfEMAddrIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecUserEMConf().deleteSecUserEMConfByConfEMAddrIdx( null,
				ConfirmEMailAddr );
		}
		deepDisposeSecUserEMConfByConfEMAddrIdx( ConfirmEMailAddr );
	}

	@Override
	public void deleteSecUserEMConfBySentStampIdx( LocalDateTime EMailSentStamp )
	{
		ICFSecSecUserEMConfBySentStampIdxKey key = schema.getCFSecBackingStore().getFactorySecUserEMConf().newBySentStampIdxKey();
		key.setRequiredEMailSentStamp( EMailSentStamp );
		if( indexBySentStampIdx == null ) {
			indexBySentStampIdx = new HashMap< ICFSecSecUserEMConfBySentStampIdxKey,
				Map< CFLibDbKeyHash256, ICFSecSecUserEMConfObj > >();
		}
		if( indexBySentStampIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFSecSecUserEMConfObj> dict = indexBySentStampIdx.get( key );
			schema.getCFSecBackingStore().getTableSecUserEMConf().deleteSecUserEMConfBySentStampIdx( null,
				EMailSentStamp );
			Iterator<ICFSecSecUserEMConfObj> iter = dict.values().iterator();
			ICFSecSecUserEMConfObj obj;
			List<ICFSecSecUserEMConfObj> toForget = new LinkedList<ICFSecSecUserEMConfObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexBySentStampIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecUserEMConf().deleteSecUserEMConfBySentStampIdx( null,
				EMailSentStamp );
		}
		deepDisposeSecUserEMConfBySentStampIdx( EMailSentStamp );
	}

	@Override
	public void deleteSecUserEMConfByNewAcctIdx( boolean NewAccount )
	{
		ICFSecSecUserEMConfByNewAcctIdxKey key = schema.getCFSecBackingStore().getFactorySecUserEMConf().newByNewAcctIdxKey();
		key.setRequiredNewAccount( NewAccount );
		if( indexByNewAcctIdx == null ) {
			indexByNewAcctIdx = new HashMap< ICFSecSecUserEMConfByNewAcctIdxKey,
				Map< CFLibDbKeyHash256, ICFSecSecUserEMConfObj > >();
		}
		if( indexByNewAcctIdx.containsKey( key ) ) {
			Map<CFLibDbKeyHash256, ICFSecSecUserEMConfObj> dict = indexByNewAcctIdx.get( key );
			schema.getCFSecBackingStore().getTableSecUserEMConf().deleteSecUserEMConfByNewAcctIdx( null,
				NewAccount );
			Iterator<ICFSecSecUserEMConfObj> iter = dict.values().iterator();
			ICFSecSecUserEMConfObj obj;
			List<ICFSecSecUserEMConfObj> toForget = new LinkedList<ICFSecSecUserEMConfObj>();
			while( iter.hasNext() ) {
				obj = iter.next();
				toForget.add( obj );
			}
			iter = toForget.iterator();
			while( iter.hasNext() ) {
				obj = iter.next();
				obj.forget();
			}
			indexByNewAcctIdx.remove( key );
		}
		else {
			schema.getCFSecBackingStore().getTableSecUserEMConf().deleteSecUserEMConfByNewAcctIdx( null,
				NewAccount );
		}
		deepDisposeSecUserEMConfByNewAcctIdx( NewAccount );
	}
}